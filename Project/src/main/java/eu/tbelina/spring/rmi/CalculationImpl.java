package eu.tbelina.spring.rmi;

public class CalculationImpl implements Calculation{  
	  
    @Override  
    public int cube(int number) {  
        return number*number*number;  
    }  
  
}  
