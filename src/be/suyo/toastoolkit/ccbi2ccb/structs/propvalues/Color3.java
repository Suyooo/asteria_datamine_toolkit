package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Color3 extends PropertyValue {
    private int r, g, b;

    public Color3(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Color3[" + r + "," + g + "," + b + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(3);
        arr.setValue(0, new NSNumber(r));
        arr.setValue(1, new NSNumber(g));
        arr.setValue(2, new NSNumber(b));
        return arr;
    }

}
