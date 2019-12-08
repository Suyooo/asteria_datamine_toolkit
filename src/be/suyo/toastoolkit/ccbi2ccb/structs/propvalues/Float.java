package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Float extends PropertyValue {
    private double f;

    public Float(double f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "Float[" + f + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSNumber(f);
    }

}
