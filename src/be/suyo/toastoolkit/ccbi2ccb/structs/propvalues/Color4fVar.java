package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Color4fVar extends PropertyValue {
    private double r, g, b, a, rVar, gVar, bVar, aVar;

    public Color4fVar(double r, double g, double b, double a, double rVar, double gVar, double bVar, double aVar) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.rVar = rVar;
        this.gVar = gVar;
        this.bVar = bVar;
        this.aVar = aVar;
    }

    @Override
    public String toString() {
        return "Color4fVar[" + r + "," + g + "," + b + "," + a + "," + rVar + "," + gVar + "," + bVar + "," + aVar + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        NSArray arr2 = new NSArray(4);
        arr2.setValue(0, new NSNumber(r));
        arr2.setValue(1, new NSNumber(g));
        arr2.setValue(2, new NSNumber(b));
        arr2.setValue(3, new NSNumber(a));
        NSArray arr3 = new NSArray(4);
        arr3.setValue(0, new NSNumber(rVar));
        arr3.setValue(1, new NSNumber(gVar));
        arr3.setValue(2, new NSNumber(bVar));
        arr3.setValue(3, new NSNumber(aVar));
        arr.setValue(0, arr2);
        arr.setValue(1, arr3);
        return arr;
    }

}
