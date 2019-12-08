package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSArray;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;
import be.suyo.toastoolkit.ccbi2ccb.structs.TargetType;

public class BlockCcControl extends PropertyValue {
    private String selectorName;
    private TargetType selectorTarget;
    private long controlEvents;
    
    public BlockCcControl(String selectorName, long selectorTargetId, long controlEvents) {
        this.selectorName = selectorName;
        this.selectorTarget = TargetType.values()[(int) selectorTargetId];
        this.controlEvents = controlEvents;
    }
    
    @Override
    public String toString() {
        return "BlockCcControl[" + selectorName + "," + selectorTarget + "," + controlEvents + "]";
    }

    @Override
    public NSObject toPlist() {
        NSArray arr = new NSArray(3);
        arr.setValue(0, new NSString(selectorName));
        arr.setValue(1, new NSNumber(selectorTarget.ordinal()));
        arr.setValue(2, new NSNumber(controlEvents));
        return arr;
    }

}
