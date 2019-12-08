package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Texture extends PropertyValue {
    private String spriteFile;
    
    public Texture(String spriteFile) {
        this.spriteFile = spriteFile;
    }
    
    @Override
    public String toString() {
        return "Texture[" + spriteFile + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(spriteFile);
    }

}
