package be.suyo.toastoolkit.ccbi2ccb.structs;

import java.util.Iterator;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.util.NodeIterator;

public class Node implements Iterable<Node> {
    public String className;
    public String jsController;
    public TargetType memberVarAssignmentType;
    public String memberVarAssignmentName;
    public NodeSequence[] nodeSequences;
    public Property[] properties;
    public Node[] children;

    public NSDictionary toPlist() {
        NSDictionary dict = new NSDictionary();
        // TODO: customclass? baseclass?
        dict.put("displayName", new NSString(className));
        dict.put("memberVarAssignmentName", new NSString(memberVarAssignmentName));
        dict.put("memberVarAssignmentType", new NSNumber(memberVarAssignmentType.ordinal()));
        
        NSDictionary seqDict = new NSDictionary();
        for (NodeSequence s : nodeSequences) {
            seqDict.put("" + s.sequenceId, s.toPlist());
        }
        dict.put("animatedProperties", seqDict);
        
        NSArray propArray = new NSArray(properties.length);
        for (int i = 0; i < properties.length; i++) {
            propArray.setValue(i, properties[i].toPlist());
        }
        dict.put("properties", propArray);
        
        NSArray childArray = new NSArray(children.length);
        for (int i = 0; i < children.length; i++) {
            childArray.setValue(i, children[i].toPlist());
        }
        dict.put("children", childArray);
        
        return dict;
    }
    
    public String toString() {
        return toString(0);
    }
    public String toString(int offset) {
        StringBuilder s = new StringBuilder(new String(new char[offset]).replace("\0", " ") + "Node " + className);
        for (Node child : children) {
            s.append("\n").append(child.toString(offset + 4));
        }
        return s.toString();
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeIterator(this);
    }
}