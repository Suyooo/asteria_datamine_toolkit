package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.TargetType;

public class Block extends PropertyValue {
    private String selectorName;
    private TargetType selectorTarget;
    
    public Block(String selectorName, long selectorTargetId) {
        this.selectorName = selectorName;
        this.selectorTarget = TargetType.values()[(int) selectorTargetId];
    }
    
    @Override
    public String toString() {
        return "Block[" + selectorName + "," + selectorTarget + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(2);
        arr.setValue(0, new NSString(selectorName));
        arr.setValue(1, new NSNumber(selectorTarget.ordinal()));
        return arr;
    }

}
