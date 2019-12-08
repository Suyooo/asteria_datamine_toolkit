package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Text extends PropertyValue {
    private String txt;
    
    public Text(String txt) {
        this.txt = txt;
    }
    
    @Override
    public String toString() {
        return "Text[" + txt + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(txt);
    }

}
