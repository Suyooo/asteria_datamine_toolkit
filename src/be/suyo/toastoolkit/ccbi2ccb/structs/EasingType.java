package be.suyo.toastoolkit.ccbi2ccb.structs;

public enum EasingType {
    INSTANT(false), LINEAR(false), CUBIC_IN(true), CUBIC_OUT(true), CUBIC_INOUT(true), ELASTIC_IN(true),
    ELASTIC_OUT(true), ELASTIC_INOUT(true), BOUNCE_IN(false), BOUNCE_OUT(false), BOUNCE_INOUT(false), BACK_IN(false),
    BACK_OUT(false), BACK_INOUT(false);

    public final boolean hasOpt;

    EasingType(boolean hasOpt) {
        this.hasOpt = hasOpt;
    }
}