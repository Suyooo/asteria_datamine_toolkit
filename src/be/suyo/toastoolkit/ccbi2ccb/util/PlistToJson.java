package be.suyo.toastoolkit.ccbi2ccb.util;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;

public class PlistToJson {
    public static String convert(NSObject p) {
        if (p instanceof NSString) {
            NSString ps = (NSString) p;
            return "\"" + ps.getContent().replaceAll("\n", "\\\\n").replaceAll("\"", "\\\\\"") + "\"";
        } else if (p instanceof NSNumber) {
            NSNumber pn = (NSNumber) p;
            return pn.stringValue();
        } else if (p instanceof NSDictionary) {
            NSDictionary pd = (NSDictionary) p;
            String[] strs = new String[pd.count()];
            int i = 0;
            for (String key : pd.allKeys()) {
                strs[i++] = "\"" + key + "\":" + convert(pd.objectForKey(key));
            }
            return "{" + String.join(",", strs) + "}";
        } else if (p instanceof NSArray) {
            NSArray pa = (NSArray) p;
            String[] strs = new String[pa.count()];
            for (int i = 0; i < pa.count(); i++) {
                strs[i] = convert(pa.objectAtIndex(i));
            }
            return "[" + String.join(",", strs) + "]";
        } else {
            throw new UnsupportedOperationException("Unsupported NSObject Type " + p.getClass().getName());
        }
    }
}
