package be.suyo.toastoolkit.ccbi2ccb.structs;

import java.io.IOException;

import com.dd.plist.NSObject;

import be.suyo.toastoolkit.ccbi2ccb.CcbiFile;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.*;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.Byte;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.Float;
import be.suyo.toastoolkit.ccbi2ccb.structs.propvalues.Integer;
import be.suyo.toastoolkit.ccbi2ccb.util.CcbiException;

public abstract class PropertyValue {

    public abstract NSObject toPlist();

    public abstract String toString();

    public static PropertyValue readValueForType(CcbiFile ccbi, PropertyType type) throws IOException {
        switch (type) {
            case POSITION:
                return new Position(ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextUint());
            case SIZE:
                return new Size(ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextUint());
            case POINT:
            case POINT_LOCK:
                return new Point(ccbi.nextFloat(), ccbi.nextFloat());
            case SCALE_LOCK:
                return new ScaleLock(ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextUint());
            case DEGREES:
            case FLOAT:
                return new Float(ccbi.nextFloat());
            case INTEGER:
            case INTEGER_LABELED:
                return new Integer(ccbi.nextSint());
            case FLOAT_VAR:
                return new FloatVar(ccbi.nextFloat(), ccbi.nextFloat());
            case CHECK:
                return new Check(ccbi.nextBoolean());
            case SPRITE_FRAME:
                return new SpriteFrame(ccbi.nextCString(), ccbi.nextCString());
            case TEXTURE:
                return new Texture(ccbi.nextCString());
            case BYTE:
                return new Byte(ccbi.nextByte());
            case COLOR3:
                return new Color3(ccbi.nextByte(), ccbi.nextByte(), ccbi.nextByte());
            case COLOR4F_VAR:
                return new Color4fVar(ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextFloat(),
                        ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextFloat(), ccbi.nextFloat());
            case FLIP:
                return new Flip(ccbi.nextBoolean(), ccbi.nextBoolean());
            case BLENDMODE:
                return new Blendmode(ccbi.nextUint(), ccbi.nextUint());
            case FNT_FILE:
                return new FntFile(ccbi.nextCString());
            case TEXT:
            case STRING:
                return new Text(ccbi.nextCString());
            case FONT_TTF:
                return new FontTtf(ccbi.nextCString());
            case BLOCK:
                return new Block(ccbi.nextCString(), ccbi.nextUint());
            case ANIMATION:
                return new Animation(ccbi.nextCString(), ccbi.nextCString());
            case CCB_FILE:
                return new CcbFile(ccbi.nextCString());
            case BLOCK_CC_CONTROL:
                return new BlockCcControl(ccbi.nextCString(), ccbi.nextUint(), ccbi.nextUint());
            case FLOAT_SCALE:
                return new FloatScale(ccbi.nextFloat(), ccbi.nextUint());
            case FLOAT_XY:
                return new FloatXY(ccbi.nextFloat(), ccbi.nextFloat());
            default:
                throw new CcbiException("Unimplemented property type " + type + " (" + type.ordinal() + ")");
        }
    }

    public static PropertyValue readValueForAnimationType(CcbiFile ccbi, PropertyType type) throws IOException {
        switch (type) {
            case POSITION:
                return new Position(ccbi.nextFloat(), ccbi.nextFloat(), -1);
            case SCALE_LOCK:
                return new ScaleLock(ccbi.nextFloat(), ccbi.nextFloat(), -1);
            case DEGREES:
                return new Float(ccbi.nextFloat());
            case CHECK:
                return new Check(ccbi.nextBoolean());
            case SPRITE_FRAME:
                return new SpriteFrame(ccbi.nextCString(), ccbi.nextCString());
            case BYTE:
                return new Byte(ccbi.nextByte());
            case COLOR3:
                return new Color3(ccbi.nextByte(), ccbi.nextByte(), ccbi.nextByte());
            case FLOAT_XY:
                return new FloatXY(ccbi.nextFloat(), ccbi.nextFloat());
            default:
                throw new CcbiException("Unimplemented animation property type " + type + " (" + type.ordinal() + ")");
        }
    }
}