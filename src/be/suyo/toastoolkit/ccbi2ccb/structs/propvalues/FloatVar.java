package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class FloatVar extends PropertyValue {
    private double f, fVar;
    
    public FloatVar(double f, double fVar) {
        this.f = f;
        this.fVar = fVar;
    }
    
    @Override
    public String toString() {
        return "FloatVar[" + f + "," + fVar + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSNumber(f));
        arr.setValue(1, new NSNumber(fVar));
        return arr;
    }

}
