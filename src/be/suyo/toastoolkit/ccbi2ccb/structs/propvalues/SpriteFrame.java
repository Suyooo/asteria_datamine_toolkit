package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class SpriteFrame extends PropertyValue {
    private String spriteSheet, spriteFile;
    
    public SpriteFrame(String spriteSheet, String spriteFile) {
        this.spriteSheet = spriteSheet;
        this.spriteFile = spriteFile;
    }
    
    public String getSpriteSheet() {
        return spriteSheet;
    }
    
    @Override
    public String toString() {
        return "SpriteFrame[" + spriteSheet + "," + spriteFile + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSString(spriteSheet));
        arr.setValue(1, new NSString(spriteFile));
        return arr;
    }

}
