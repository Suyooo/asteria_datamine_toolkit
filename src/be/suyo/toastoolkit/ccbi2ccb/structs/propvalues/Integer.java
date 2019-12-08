package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Integer extends PropertyValue {
    private long d;

    public Integer(long d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Integer[" + d + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSNumber(d);
    }

}
