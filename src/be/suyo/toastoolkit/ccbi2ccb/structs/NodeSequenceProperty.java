package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;

public class NodeSequenceProperty {
    public String name;
    public PropertyType type;
    public Keyframe[] keyframes;

    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        dict.put("name", new NSString(name));
        dict.put("type", new NSString(type.outputString));
        NSArray keyArray = new NSArray(keyframes.length);
        for (int i = 0; i < keyframes.length; i++) {
            NSDictionary k = keyframes[i].toPlist();
            k.put("name", new NSString(name));
            k.put("type", new NSString(type.outputString));
            keyArray.setValue(i, k);
        }
        dict.put("keyframes", keyArray);
        return dict;
    }
}