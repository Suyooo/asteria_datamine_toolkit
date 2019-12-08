package be.suyo.toastoolkit.ccbi2ccb;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.CallbackKeyframe;
import be.suyo.toastoolkit.ccbi2ccb.structs.EasingType;
import be.suyo.toastoolkit.ccbi2ccb.structs.Keyframe;
import be.suyo.toastoolkit.ccbi2ccb.structs.Node;
import be.suyo.toastoolkit.ccbi2ccb.structs.NodeSequence;
import be.suyo.toastoolkit.ccbi2ccb.structs.NodeSequenceProperty;
import be.suyo.toastoolkit.ccbi2ccb.structs.Platform;
import be.suyo.toastoolkit.ccbi2ccb.structs.Property;
import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyType;
import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.Sequence;
import be.suyo.toastoolkit.ccbi2ccb.structs.SoundKeyframe;
import be.suyo.toastoolkit.ccbi2ccb.structs.TargetType;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.SpriteFrame;
import be.suyo.toastoolkit.ccbi2ccb.util.CcbiException;
import be.suyo.toastoolkit.ccbi2ccb.util.PlistToJson;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3 || !(args[0].equals("ccb") || args[0].equals("maa"))) {
            System.err.println("Usage: Main [output mode] [ccbi filename] [output file]");
            System.err.println("Output mode is \"ccb\" for CCB(-ish) PLIST XML file, \"maa\" for MAA JSON file");
            System.err.println("Specify output as \".\" to use stdout");
            System.exit(1);
        }

        convertCcbi(args[1], args[2], args[0]);
    }

    public static void convertCcbi(String inFilename, String outFilename, String outType) {
        try {
            CcbiFile ccbi = new CcbiFile(inFilename);
            if (outType.equals("ccb")) {
                ccbi.saveAsCCB(outFilename);
            } else if (outType.equals("maa")) {
                ccbi.saveAsMAA(outFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}