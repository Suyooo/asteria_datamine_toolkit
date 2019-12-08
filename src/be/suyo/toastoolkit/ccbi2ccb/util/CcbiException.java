package be.suyo.toastoolkit.ccbi2ccb.util;

public class CcbiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CcbiException(String message) {
        super(message);
    }
    
    public CcbiException(Throwable cause) {
        super(cause);
    }

    public CcbiException(String message, Throwable cause) {
        super(message, cause);
    }
}