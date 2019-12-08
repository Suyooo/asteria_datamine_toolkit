package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;

public class Property {
    public PropertyType type;
    public String propertyName;
    public Platform platform;
    public PropertyValue value;
    
    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        dict.put("type", new NSString(type.outputString));
        if (platform != Platform.ALL) dict.put("platform", new NSString(platform.outputString));
        dict.put("name", new NSString(propertyName));
        dict.put("value", value.toPlist());
        return dict;
    }
    
    public String toString() {
        return type + " " + propertyName + " = " + value;
    }
}