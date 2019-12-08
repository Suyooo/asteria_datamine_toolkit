package be.suyo.toastoolkit.ccbi2ccb.structs;

public enum Platform {
    ALL(""), IOS("iOS"), MAC("Mac");

    public final String outputString;

    Platform(String outputString) {
        this.outputString = outputString;
    }
}
