package be.suyo.toastoolkit.ccbi2ccb;

import java.io.*;
import java.util.HashSet;

import be.suyo.toastoolkit.ccbi2ccb.structs.*;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.SpriteFrame;
import be.suyo.toastoolkit.ccbi2ccb.util.Biterator;
import be.suyo.toastoolkit.ccbi2ccb.util.CcbiException;
import be.suyo.toastoolkit.ccbi2ccb.util.PlistToJson;
import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;

public class CcbiFile {
    private DataInputStream file;

    private long version;
    private boolean usesJsController;

    private String[] stringCache;

    private Sequence[] sequences;
    private int autoPlaySequenceId;

    private Node rootNode;

    private double maxTime;

    private HashSet<String> resourcesAtlas = new HashSet<>();
    private HashSet<String> resourcesAudioVoice = new HashSet<>();
    private HashSet<String> resourcesAudioSFX = new HashSet<>();

    public HashSet<String> getResourcesAtlas() {
        return resourcesAtlas;
    }

    public HashSet<String> getResourcesAudioVoice() {
        return resourcesAudioVoice;
    }

    public HashSet<String> getResourcesAudioSFX() {
        return resourcesAudioSFX;
    }

    public CcbiFile(String filename) throws IOException {
        file = new DataInputStream(new FileInputStream(filename));

        this.parseHeader();
        this.parseStringCache();
        this.parseSequences();
        rootNode = this.parseNode();

        findResourcesAndMaxTime();
    }

    /*
     * parsing methods
     */

    private void parseHeader() throws IOException {
        // First four bytes must be "ibcc"
        if (this.nextByte() != 'i' || this.nextByte() != 'b'
                || this.nextByte() != 'c' || this.nextByte() != 'c') {
            throw new CcbiException("Invalid File Header - not a CCBI file");
        }
        version = this.nextUint();
        if (version != 5) {
            throw new CcbiException(
                    "Invalid File Version - this reader is written for v5, file is v"
                            + version);
        }
        usesJsController = this.nextBoolean();
    }

    private void parseStringCache() throws IOException {
        // Begins with an uint describing the amount of strings
        int length = (int) this.nextUint();
        stringCache = new String[length];

        for (int i = 0; i < length; i++) {
            stringCache[i] = this.nextString();
        }
    }

    private void parseSequences() throws IOException {
        // Begins with an uint describing the amount of sequences
        int length = (int) this.nextUint();
        sequences = new Sequence[length];

        for (int i = 0; i < length; i++) {
            sequences[i] = new Sequence();
            sequences[i].duration = this.nextFloat();
            sequences[i].name = this.nextCString();
            sequences[i].sequenceId = this.nextUint();
            sequences[i].chainedSequenceId = this.nextSint();

            int callbackLength = (int) this.nextUint();
            sequences[i].callbacks = new CallbackKeyframe[callbackLength];
            for (int j = 0; j < callbackLength; j++) {
                sequences[i].callbacks[j] = new CallbackKeyframe();
                sequences[i].callbacks[j].time = this.nextFloat();
                sequences[i].callbacks[j].callbackName = this.nextCString();
                sequences[i].callbacks[j].callbackType = this.nextUint();
            }

            int soundLength = (int) this.nextUint();
            sequences[i].sounds = new SoundKeyframe[soundLength];
            for (int j = 0; j < soundLength; j++) {
                sequences[i].sounds[j] = new SoundKeyframe();
                sequences[i].sounds[j].time = this.nextFloat();
                sequences[i].sounds[j].soundFile = this.nextCString();
                sequences[i].sounds[j].pitch = this.nextFloat();
                sequences[i].sounds[j].pan = this.nextFloat();
                sequences[i].sounds[j].gain = this.nextFloat();
            }
        }

        autoPlaySequenceId = (int) this.nextUint();
    }

    private Node parseNode() throws IOException {
        Node n = new Node();

        n.className = this.nextCString();
        System.out.println("Reading node of class " + n.className);
        if (usesJsController) {
            n.jsController = this.nextCString();
        } else {
            n.jsController = "";
        }
        n.memberVarAssignmentType = TargetType.values()[(int) this.nextUint()];
        if (n.memberVarAssignmentType != TargetType.NONE) {
            n.memberVarAssignmentName = this.nextCString();
        } else {
            n.memberVarAssignmentName = "";
        }

        int nodeSeqLength = (int) this.nextUint();
        n.nodeSequences = new NodeSequence[nodeSeqLength];
        System.out.println(nodeSeqLength + " node sequences");
        for (int i = 0; i < nodeSeqLength; i++) {
            n.nodeSequences[i] = new NodeSequence();
            n.nodeSequences[i].sequenceId = this.nextUint();
            int seqPropLength = (int) this.nextUint();
            n.nodeSequences[i].properties = new NodeSequenceProperty[seqPropLength];
            for (int j = 0; j < seqPropLength; j++) {
                n.nodeSequences[i].properties[j] = new NodeSequenceProperty();
                n.nodeSequences[i].properties[j].name = this.nextCString();
                n.nodeSequences[i].properties[j].type =
                        PropertyType.values()[(int) this.nextUint()];

                int keyframeLength = (int) this.nextUint();
                n.nodeSequences[i].properties[j].keyframes = new Keyframe[keyframeLength];
                for (int k = 0; k < keyframeLength; k++) {
                    n.nodeSequences[i].properties[j].keyframes[k] = new Keyframe();
                    n.nodeSequences[i].properties[j].keyframes[k].time =
                            this.nextFloat();
                    n.nodeSequences[i].properties[j].keyframes[k].easingType =
                            EasingType.values()[(int) this.nextUint()];
                    if (n.nodeSequences[i].properties[j].keyframes[k].easingType.hasOpt) {
                        n.nodeSequences[i].properties[j].keyframes[k].easingOpt =
                                this.nextFloat();
                    }
                    n.nodeSequences[i].properties[j].keyframes[k].value = PropertyValue
                            .readValueForAnimationType(this, n.nodeSequences[i].properties[j].type);
                }
            }
        }

        int propLength = (int) (this.nextUint() + this.nextUint());
        n.properties = new Property[propLength];
        System.out.println(propLength + " properties");
        for (int i = 0; i < propLength; i++) {
            n.properties[i] = new Property();
            n.properties[i].type = PropertyType.values()[(int) this.nextUint()];
            n.properties[i].propertyName = this.nextCString();
            n.properties[i].platform = Platform.values()[this.nextByte()];
            n.properties[i].value = PropertyValue.readValueForType(this, n.properties[i].type);
            System.out.println("  " + n.properties[i]);
        }

        int childrenLength = (int) this.nextUint();
        n.children = new Node[childrenLength];
        System.out.println(childrenLength + " children");
        for (int i = 0; i < childrenLength; i++) {
            n.children[i] = parseNode();
        }

        return n;
    }

    public void findResourcesAndMaxTime() {
        for (Node n : rootNode) {
            for (NodeSequence ns : n.nodeSequences) {
                for (NodeSequenceProperty nsp : ns.properties) {
                    for (Keyframe k : nsp.keyframes) {
                        if (k.time > maxTime)
                            maxTime = k.time;
                    }
                }
            }
            for (Property np : n.properties) {
                if (np.propertyName.equals("displayFrame")) {
                    SpriteFrame npv = (SpriteFrame) np.value;
                    String s = npv.getSpriteSheet();
                    if (!s.isEmpty()) {
                        resourcesAtlas.add(s.substring(0, s.length() - 6));
                    }
                }
            }
        }
        for (Sequence s : sequences) {
            for (CallbackKeyframe sck : s.callbacks) {
                if (sck.time > maxTime)
                    maxTime = sck.time;

                if (sck.callbackName.startsWith("seplay_")) {
                    resourcesAudioSFX.add(sck.callbackName.substring(7));
                } else if (sck.callbackName.startsWith("voiceplay_")) {
                    resourcesAudioVoice
                            .add(sck.callbackName.substring(10).replaceFirst("_", "/"));
                }
            }
            for (SoundKeyframe ssk : s.sounds) {
                if (ssk.time > maxTime)
                    maxTime = ssk.time;
            }
        }
    }

    /*
     * reading methods
     */

    // BYTE: unsigned integer, so we can't use the byte type
    public int nextByte() throws IOException {
        return file.readUnsignedByte();
    }

    // BOOLEAN
    public boolean nextBoolean() throws IOException {
        return file.readBoolean();
    }

    // Elias gamma encoding. Used as helper for other data types
    private long nextEliasInt() throws IOException {
        try {
            int pot = 1;
            Biterator it = new Biterator(file);
            while (!it.next()) {
                pot <<= 1;
            }

            int res = pot;
            while (pot > 1) {
                pot >>>= 1;
                if (it.next()) {
                    res += pot;
                }
            }

            return res;
        } catch (CcbiException e) {
            throw (IOException) e.getCause();
        }
    }

    // UINT: 1 is added to the value so the number 0 is supported, so subtract it when reading.
    public long nextUint() throws IOException {
        return nextEliasInt() - 1;
    }

    // SINT: Integers are transformed using bijection when storing: (0, -1, 1, -2, 2, ...) => (1, 2, 3, 4, 5, ...)
    public long nextSint() throws IOException {
        long elias = nextEliasInt();
        long res = elias >>> 1;
        if ((elias & 1L) == 0) {
            return -res;
        } else {
            return res;
        }
    }

    // FLOAT: has six different ways it can be stored
    public double nextFloat() throws IOException {
        byte type = file.readByte();
        switch (type) {
            case 0: // is always 0
                return 0.0;
            case 1: // is always 1
                return 1.0;
            case 2: // is always -1
                return -1.0;
            case 3: // is always 0.5
                return 0.5;
            case 4: // saved as int
                return nextSint();
            case 5: // saved as float
                // Floats are saved in the opposite endianness Java expects - we can't use readFloat
                // So here's a "reimplementation" of it (along with readInt) to make it work
                byte d = file.readByte();
                byte c = file.readByte();
                byte b = file.readByte();
                byte a = file.readByte();
                int i = (((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff));
                float f = Float.intBitsToFloat(i);

                if (!Float.isFinite(f)) {
                    throw new CcbiException("Invalid float " + f);
                }
                return f;
            default:
                throw new CcbiException("Unknown float type " + type);
        }
    }

    // STRING: ccbi stores string in Java-style UTF8 so hey we're in luck
    public String nextString() throws IOException {
        return file.readUTF();
    }

    // CSTRING: load a cached string from stringCache
    public String nextCString() throws IOException {
        return stringCache[(int) nextUint()];
    }

    /*
     * saving methods
     */

    public void saveAsCCB(String filename) throws IOException {
        NSDictionary dict = new NSDictionary();
        dict.put("fileType", new NSString("CocosBuilder"));
        dict.put("fileVersion", new NSNumber(version));
        dict.put("jsControlled", new NSNumber(this.usesJsController));
        dict.put("currentSequenceId", new NSNumber(this.autoPlaySequenceId));
        dict.put("guides", new NSArray(0));
        dict.put("notes", new NSArray(0));
        dict.put("nodeGraph", this.rootNode.toPlist());

        NSArray seqArray = new NSArray(this.sequences.length);
        for (int i = 0; i < this.sequences.length; i++) {
            seqArray.setValue(i, this.sequences[i].toPlist(this.autoPlaySequenceId));
        }
        dict.put("sequences", seqArray);

        save(filename, dict.toXMLPropertyList());
    }

    public void saveAsMAA(String filename) throws IOException {
        NSDictionary dict = new NSDictionary();
        dict.put("autoPlaySequenceId", new NSNumber(this.autoPlaySequenceId));
        dict.put("rootNode", this.rootNode.toPlist());

        NSArray seqArray = new NSArray(this.sequences.length);
        for (int i = 0; i < this.sequences.length; i++) {
            seqArray.setValue(i, this.sequences[i].toPlist(this.autoPlaySequenceId));
        }
        dict.put("sequences", seqArray);

        dict.put("endTime", new NSNumber(this.maxTime));
        NSDictionary resDict = new NSDictionary();

        NSArray resAtlasArr = new NSArray(this.resourcesAtlas.size());
        int i = 0;
        for (String s : this.resourcesAtlas) {
            resAtlasArr.setValue(i++, s);
        }
        resDict.put("atlas", resAtlasArr);

        NSArray resSoundSArr = new NSArray(this.resourcesAudioSFX.size());
        i = 0;
        for (String s : this.resourcesAudioSFX) {
            resSoundSArr.setValue(i++, s);
        }
        resDict.put("sfx", resSoundSArr);

        NSArray resSoundVArr = new NSArray(this.resourcesAudioVoice.size());
        i = 0;
        for (String s : this.resourcesAudioVoice) {
            resSoundVArr.setValue(i++, s);
        }
        resDict.put("voice", resSoundVArr);

        dict.put("resources", resDict);

        save(filename, "var ccb = " + PlistToJson.convert(dict));
    }

    private void save(String filename, String data) throws IOException {
        if (filename.equals("."))
            System.out.println(data);
        else {
            PrintWriter outfile = new PrintWriter(filename);
            outfile.write(data);
            outfile.close();
        }
    }
}
