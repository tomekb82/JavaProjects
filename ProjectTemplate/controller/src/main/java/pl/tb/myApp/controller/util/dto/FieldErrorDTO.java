package pl.tb.myApp.controller.util.dto;

/**
 * Created by tomek on 25.10.15.
 */
public class FieldErrorDTO {

    private String path;
    private String message;
    public FieldErrorDTO(String path, String message) {
        this.path = path;
        this.message = message;
    }
    public String getPath() {
        return path;
    }
    public String getMessage() {
        return message;
    }
}
