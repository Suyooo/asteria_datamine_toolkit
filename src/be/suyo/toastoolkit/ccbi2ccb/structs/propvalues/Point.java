package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Point extends PropertyValue {
    private double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Point[" + x + "," + y + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSNumber(x));
        arr.setValue(1, new NSNumber(y));
        return arr;
    }

}
