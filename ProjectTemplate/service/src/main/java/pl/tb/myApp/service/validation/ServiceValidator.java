package pl.tb.myApp.service.validation;

import pl.tb.myApp.model.validation.ErrorMessage;
import pl.tb.myApp.model.validation.MessagePriority;
import pl.tb.myApp.model.validation.ValidationMessage;

public class ServiceValidator {

    public static ValidationMessage getErrorMessage(String fieldName, ErrorMessage errorMessage){
        return podajKomunikat(fieldName, MessagePriority.ERROR, errorMessage);
    }

    public static ValidationMessage getWarningMessage(String fieldName, ErrorMessage errorMessage){
        return podajKomunikat(fieldName, MessagePriority.WARNING, errorMessage);
    }

    public static ValidationMessage getInfoMessage(String fieldName, ErrorMessage errorMessage){
        return podajKomunikat(fieldName, MessagePriority.INFO, errorMessage);
    }

    private static ValidationMessage podajKomunikat(String fieldName,MessagePriority priority,ErrorMessage errorMessage){
        ValidationMessage pKomunikat=new ValidationMessage();
        pKomunikat.setPriority(priority);
        pKomunikat.setFieldName(fieldName);
        pKomunikat.setMessage(errorMessage != null ? errorMessage.getMessage() : null);
        return pKomunikat;
    }
}
