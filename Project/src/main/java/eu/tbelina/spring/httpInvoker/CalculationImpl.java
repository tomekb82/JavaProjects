package eu.tbelina.spring.httpInvoker;

public class CalculationImpl implements Calculation{  
	  
    @Override  
    public int cube(int number) {  
        return number*number*number;  
    }  
  
}  
