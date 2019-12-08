package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class FontTtf extends PropertyValue {
    private String fntFile;
    
    public FontTtf(String fntFile) {
        this.fntFile = fntFile;
    }
    
    @Override
    public String toString() {
        return "FontTtf[" + fntFile + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(fntFile);
    }

}
