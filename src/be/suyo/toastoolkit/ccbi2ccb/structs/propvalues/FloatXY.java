package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class FloatXY extends PropertyValue {
    private double xFloat, yFloat;
    
    public FloatXY(double xFloat, double yFloat) {
        this.xFloat = xFloat;
        this.yFloat = yFloat;
    }
    
    @Override
    public String toString() {
        return "FloatVar[" + xFloat + "," + yFloat + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSNumber(xFloat));
        arr.setValue(1, new NSNumber(yFloat));
        return arr;
    }

}
