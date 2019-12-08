package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class FntFile extends PropertyValue {
    private String fntFile;
    
    public FntFile(String fntFile) {
        this.fntFile = fntFile;
    }
    
    @Override
    public String toString() {
        return "FontFile[" + fntFile + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(fntFile);
    }

}
