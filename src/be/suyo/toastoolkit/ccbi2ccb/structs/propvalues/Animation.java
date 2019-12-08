package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Animation extends PropertyValue {
    private String animationFile, animation;
    
    public Animation(String animationFile, String animation) {
        this.animationFile = animationFile;
        this.animation = animation;
    }
    
    @Override
    public String toString() {
        return "Animation[" + animationFile + "," + animation + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSString(animationFile));
        arr.setValue(1, new NSString(animation));
        return arr;
    }

}
