package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSDictionary;

public class NodeSequence {
    public long sequenceId;
    public NodeSequenceProperty[] properties;
    
    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        for (NodeSequenceProperty sp : properties) {
            dict.put(sp.name, sp.toPlist());
        }
        return dict;
    }
}