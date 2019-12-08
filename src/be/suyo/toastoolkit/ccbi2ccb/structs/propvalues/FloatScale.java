package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.ScaleType;

public class FloatScale extends PropertyValue {
    private double f;
    private ScaleType type;

    public FloatScale(double f, long typeId) {
        this.f = f;
        this.type = ScaleType.values()[(int) typeId];
    }

    @Override
    public String toString() {
        return "FloatScale[" + f + "," + type + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSNumber(f));
        arr.setValue(1, new NSNumber(type.ordinal()));
        return arr;
    }

}
