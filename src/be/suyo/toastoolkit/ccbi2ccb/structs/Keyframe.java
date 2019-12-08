package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;

public class Keyframe {
    public double time;
    public EasingType easingType;
    public double easingOpt;
    public PropertyValue value;

    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        dict.put("time", new NSNumber(time));
        dict.put("value", value.toPlist());

        NSDictionary easingDict = new NSDictionary();
        easingDict.put("type", new NSNumber(easingType.ordinal()));
        if (easingType.hasOpt) {
            easingDict.put("opt", new NSNumber(easingOpt)); // TODO: IS THIS CORRECT???
        }
        dict.put("easing", easingDict);

        return dict;
    }
}