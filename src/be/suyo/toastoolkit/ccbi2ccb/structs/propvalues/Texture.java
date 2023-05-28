package be.suyo.toastoolkit.ccbi2ccb.structs.propvalues;

import com.dd.plist.NSObject;
import com.dd.plist.NSString;

import be.suyo.toastoolkit.ccbi2ccb.structs.PropertyValue;

public class Texture extends PropertyValue {
    private String textureFile;
    
    public Texture(String textureFile) {
        this.textureFile = textureFile;
    }

    public String getTextureFile() {
        return textureFile;
    }
    
    @Override
    public String toString() {
        return "Texture[" + textureFile + "]";
    }

    @Override
    public NSObject toPlist() {
        return new NSString(textureFile);
    }

}
