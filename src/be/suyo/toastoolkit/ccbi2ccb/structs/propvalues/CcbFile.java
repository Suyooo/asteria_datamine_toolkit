package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class CcbFile extends PropertyValue {
    private String ccbFile;
    
    public CcbFile(String ccbFile) {
        this.ccbFile = ccbFile;
    }
    
    @Override
    public String toString() {
        return "CCBFile[" + ccbFile + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(ccbFile);
    }

}
