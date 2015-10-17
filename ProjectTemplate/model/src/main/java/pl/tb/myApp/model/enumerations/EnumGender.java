package pl.tb.myApp.model.enumerations;

/**
 * Created by tomek on 17.10.15.
 */
public enum EnumGender {
    F("female"),  // Female
    M("male");   // Male

    private final String value;

    public String value() {
        return value;
    }

    EnumGender(String v) {
        value = v;
    }

    public static EnumGender fromValue(String v) {
        for (EnumGender c: EnumGender.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
