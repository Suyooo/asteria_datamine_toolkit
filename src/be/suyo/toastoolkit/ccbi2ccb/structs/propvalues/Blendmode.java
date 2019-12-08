package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Blendmode extends PropertyValue {
    private long src, dst;

    public Blendmode(long src, long dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "BlendMode[" + src + "," + dst + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSNumber(src));
        arr.setValue(1, new NSNumber(dst));
        return arr;
    }

}
