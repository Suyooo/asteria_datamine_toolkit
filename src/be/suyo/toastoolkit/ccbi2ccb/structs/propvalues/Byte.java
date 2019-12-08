package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Byte extends PropertyValue {
    private int b;

    public Byte(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Byte[" + b + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSNumber(b);
    }

}
