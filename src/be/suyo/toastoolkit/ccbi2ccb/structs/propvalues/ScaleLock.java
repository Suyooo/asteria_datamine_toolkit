package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.ScaleType;

public class ScaleLock extends PropertyValue {
    private double x, y;
    private ScaleType type;
    
    public ScaleLock(double x, double y, long typeId) {
        this.x = x;
        this.y = y;
        if (typeId != -1) this.type = ScaleType.values()[(int) typeId];
    }

    @Override
    public String toString() {
        return "ScaleLock[" + x + "," + y + "," + type + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(type != null ? 3 : 2);
        arr.setValue(0, new NSNumber(x));
        arr.setValue(1, new NSNumber(y));
        if (type != null) arr.setValue(2, new NSNumber(type.ordinal()));
        return arr;
    }
}
