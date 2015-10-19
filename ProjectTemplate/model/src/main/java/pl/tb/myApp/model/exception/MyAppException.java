package pl.tb.myApp.model.exception;

import pl.tb.myApp.model.validation.ValidationMessage;

import java.util.ArrayList;
import java.util.List;


public class MyAppException extends Exception {

    private boolean isCriticalError=false;

    private List<ValidationMessage> listaKomunikatow=new ArrayList<ValidationMessage>();

    public MyAppException() {
        super();
        isCriticalError=true;
    }

    public MyAppException(ValidationMessage aKomunikat) {
        isCriticalError=false;
        listaKomunikatow.add(aKomunikat);
    }

    public MyAppException(List<ValidationMessage> aListaKomunikatow) {
        isCriticalError=false;
        listaKomunikatow=aListaKomunikatow;
    }

    public MyAppException(Throwable cause) {
        super(cause);
        isCriticalError=true;
    }

    public List<ValidationMessage> getListaKomunikatow() {
        return listaKomunikatow;
    }

    public boolean isCriticalError() {
        return isCriticalError;
    }

    public void setIsCriticalError(boolean isCriticalError) {

    }


}