package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;

public class CallbackKeyframe {
    public double time;
    public String callbackName;
    public long callbackType;

    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        dict.put("time", new NSNumber(time));
        dict.put("type", new NSNumber(10));
        dict.put("value", new NSArray(new NSString(callbackName), new NSNumber(callbackType)));
        
        NSDictionary easeDict = new NSDictionary();
        easeDict.put("type", new NSNumber(0));
        dict.put("easing", easeDict);
        return dict;
    }
}
