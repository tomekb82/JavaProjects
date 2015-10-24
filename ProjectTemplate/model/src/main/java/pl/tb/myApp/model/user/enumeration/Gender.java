package pl.tb.myApp.model.user.enumeration;

/**
 * Created by tomek on 17.10.15.
 */
public enum Gender {
    F("female"),  // Female
    M("male");   // Male

    private final String value;

    public String value() {
        return value;
    }

    Gender(String v) {
        value = v;
    }

    public static Gender fromValue(String v) {
        for (Gender c: Gender.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
