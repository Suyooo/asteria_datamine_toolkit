package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.SizeType;

public class Size extends PropertyValue {
    private double width, height;
    private SizeType type;
    
    public Size(double width, double height, long typeId) {
        this.width = width;
        this.height = height;
        if (typeId != -1) this.type = SizeType.values()[(int) typeId];
    }
    
    @Override
    public String toString() {
        return "Size[" + width + "," + height + "," + type + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(3);
        arr.setValue(0, new NSNumber(width));
        arr.setValue(1, new NSNumber(height));
        arr.setValue(2, new NSNumber(type.ordinal()));
        return arr;
    }

}
