package pl.tb.myApp.controller.util.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 25.10.15.
 */
public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    public ValidationErrorDTO() {
    }
    public void addFieldError(String path, String message) {
        FieldErrorDTO fieldError = new FieldErrorDTO(path, message);
        fieldErrors.add(fieldError);
    }
    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

}
