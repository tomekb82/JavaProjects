package eu.tbelina.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class Monitor {
	
	//@Before("execution(*eu.tbelina.spring.service.impl.ExpenseService.addExpense(..))")
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
	//@Pointcut("execution(*eu.tbelina.spring.service.impl.ExpenseService.getExpenses(..))")
	//@Pointcut("execution(**.getExpenses(..))")
	public void calcultation(){
		
	}
	
	//@Before("calcultation()")
	public void beforeCalculation(){
		System.out.println("Preparing to calculation !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!.");
	}
	
	//@AfterReturning("calcultation()")
	public void afterCalculation(){
		System.out.println("After calculation.");
	}
	
	//@AfterThrowing("calcultation()")
	public void incorrectCalculation(){
		System.out.println("Incorecct calculation.");
	}
	//@Around("calcultation()")
	public void calculatePerformance(ProceedingJoinPoint joinPoint){
		
		try{
			System.out.println("Start the performance test...");
			long start = System.currentTimeMillis();
			
			joinPoint.proceed();
			
			long end = System.currentTimeMillis();
			System.out.println(" Results of the performance test:time = " + (end-start) + "ms");
		}catch(Throwable t){
			System.out.println("Performance test failed.");
		}
		
	}

}
