package pl.tb.myApp.controller.util.controller;

import pl.tb.myApp.controller.util.enumeration.ErrorType;
import pl.tb.myApp.model.util.exception.MyAppException;
import pl.tb.myApp.model.util.validation.ValidationMessage;

/**
 * Created by tomek on 17.10.15.
 */
public class BasicController {

    protected String prepareErrorMessage(MyAppException e, String operation){

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(operation);
        if(e.isCriticalError()){
            errorMessage.append(ErrorType.INTERNAL);
            errorMessage.append(e.getMessage());
        }else{
            errorMessage.append(ErrorType.APP);
            for(ValidationMessage m: e.getListaKomunikatow()) {
                errorMessage.append(m.getPriority());
                errorMessage.append(m.getFieldName());
                errorMessage.append(m.getMessage());
            }
        }

        return errorMessage.toString();
    }
}
