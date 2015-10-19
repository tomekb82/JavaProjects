package pl.tb.myApp.model.validation;


public enum ErrorMessage {
    FIELD_REQUIRED("Required field not set."),
    IDENTIFIER_REQUIRED ("Identifier required"),
    DATA_CHANAGED_BY_ANOTER_USER ("Data changed by another user.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
