package pl.tb.myApp.controller.enumeration;

/**
 * Created by tomek on 17.10.15.
 */
public enum ErrorType {
    APP("Application error."),
    INTERNAL("Internal system error.");

    private final String value;

    public String value() {
        return value;
    }

    ErrorType(String v) {
        value = v;
    }

    public static ErrorType fromValue(String v) {
        for (ErrorType c: ErrorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
