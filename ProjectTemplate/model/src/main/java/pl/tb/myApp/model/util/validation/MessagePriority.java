package pl.tb.myApp.model.util.validation;


public enum MessagePriority {
    ERROR,
    WARNING,
    INFO;

    public String value() {
        return name();
    }

    public static MessagePriority fromValue(String v) {
        return valueOf(v);
    }
}
