package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.Main;

public class Sequence {
    public double duration;
    public String name;
    public long sequenceId;
    public long chainedSequenceId;
    public CallbackKeyframe[] callbacks;
    public SoundKeyframe[] sounds;
    
    public NSDictionary toPlist(long autoPlaySequenceId) {
        NSDictionary dict = new NSDictionary();
        dict.put("autoPlay", new NSNumber(autoPlaySequenceId == sequenceId));
        dict.put("chainedSequenceId", new NSNumber(chainedSequenceId));
        dict.put("length", new NSNumber(duration));
        dict.put("name", new NSString(name));
        dict.put("offset", new NSNumber(0.0));
        dict.put("position", new NSNumber(0.0));
        dict.put("resolution", new NSNumber(30));
        dict.put("scale", new NSNumber(128));
        dict.put("sequenceId", new NSNumber(sequenceId));

        NSDictionary cbcd = new NSDictionary();
        NSArray cbArray = new NSArray(callbacks.length);
        for (int i = 0; i < callbacks.length; i++) {
            cbArray.setValue(i, callbacks[i].toPlist());
        }
        cbcd.put("keyframes", cbArray);
        cbcd.put("type", 10);
        dict.put("callbackChannel",cbcd);
        
        NSDictionary scd = new NSDictionary();
        NSArray sArray = new NSArray(sounds.length);
        for (int i = 0; i < sounds.length; i++) {
            sArray.setValue(i, sounds[i].toPlist());
        }
        scd.put("keyframes", sArray);
        scd.put("type", 9);
        dict.put("soundChannel",scd);
        return dict;
    }
    
    public String toString() {
        return "Sequence " + sequenceId + " \"" + name + "\": Duration " + duration + ", Chained " + chainedSequenceId;
    }
}