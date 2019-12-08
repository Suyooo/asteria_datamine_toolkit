package be.suyo.toastoolkit.ccbi2ccb.structs;

public enum PropertyType {
    POSITION("Position"), SIZE("Size"), POINT("Point"), POINT_LOCK("PointLock"), SCALE_LOCK("ScaleLock"),
    DEGREES("Degrees"), INTEGER("Integer"), FLOAT("Float"), FLOAT_VAR("FloatVar"), CHECK("Check"),
    SPRITE_FRAME("SpriteFrame"), TEXTURE("Texture"), BYTE("Byte"), COLOR3("Color3"), COLOR4F_VAR("Color4FVar"),
    FLIP("Flip"), BLENDMODE("Blendmode"), FNT_FILE("FntFile"), TEXT("Text"), FONT_TTF("FontTTF"),
    INTEGER_LABELED("IntegerLabeled"), BLOCK("Block"), ANIMATION("Animation"), CCB_FILE("CCBFile"), STRING("String"),
    BLOCK_CC_CONTROL("BlockCCControl"), FLOAT_SCALE("FloatScale"), FLOAT_XY("FloatXY");

    public final String outputString;

    PropertyType(String outputString) {
        this.outputString = outputString;
    }
}