package be.suyo.toastoolkit.ccbi2ccb.structs;

import com.dd.plist.NSDictionary;

public class SoundKeyframe {
    public double time;
    public String soundFile;
    public double pitch;
    public double pan;
    public double gain;

    public NSDictionary toPlist() {
        throw new UnsupportedOperationException("No idea how to serialize SoundKeyframe");
    }
}
