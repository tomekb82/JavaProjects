package com.tutorialspoint;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class MainApp_BeanFactory {
	public static void main(String[] args) {
			XmlBeanFactory factory = new XmlBeanFactory
						(new ClassPathResource("Beans.xml"));
			HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
			obj.getMessage();
	}
}


