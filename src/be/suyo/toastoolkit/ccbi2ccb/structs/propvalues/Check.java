package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Check extends PropertyValue {
    private boolean b;

    public Check(boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Check[" + b + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSNumber(b);
    }

}
