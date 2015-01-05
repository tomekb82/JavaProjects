package eu.qualent.mes.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import eu.qualent.mes.utils.DateUtils;

@FacesValidator("calendarValidator")
public class CalendarValidator implements Validator {
		
		     public void validate(FacesContext context, UIComponent component,
		        Object value) throws ValidatorException {
		    	 
		    	 Object calendarStartDateValue = component.getAttributes().get("calendarStartDate");
		    	 Object calendarStartHourValue = component.getAttributes().get("calendarStartHour");
		    	 Object calendarStopHourValue = component.getAttributes().get("calendarStopHour");
		    	 
		    	 if ( value == null || calendarStartDateValue == null || calendarStartHourValue == null || calendarStopHourValue == null){
		    		 throw new ValidatorException(new FacesMessage(
		    					FacesMessage.SEVERITY_ERROR,
		    					"Wypełnij obie daty z godzinami.",
		    					null)); 
		    	 }
		    	 
		    	 Date startDate = DateUtils.setHourDate((Date)calendarStartDateValue, (Date)calendarStartHourValue);
		    	 Date stopDate = DateUtils.setHourDate((Date)value, (Date)calendarStopHourValue);
		    	 
		    	 Date now = new Date();
		    	 
		    	 if ( stopDate.before(now) || startDate.before(now) ){
		    		 throw new ValidatorException(new FacesMessage(
		    					FacesMessage.SEVERITY_ERROR,
		    					"Obie daty nie mogą być wcześniejsze od teraz.",
		    					null)); 
		    	 }
		    	 
		    	 if ( stopDate.before(startDate)){
		    		 throw new ValidatorException(new FacesMessage(
		    					FacesMessage.SEVERITY_ERROR,
		    					"'Data od' nie może być późniejsza od 'Data do'.",
		    					null)); 
		    	 }
		    }
}

