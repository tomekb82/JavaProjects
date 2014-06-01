package com.company.documentation.factory;

import com.company.documentation.*;
import com.company.documentation.decorator.*;

public class DocumentationFactory extends AbstractDocumentationFactory{

	public DocumentationInterface createDocDK(String name){
		return new DocumentationDecoratorDK(new Documentation(name)); 
	}
	
	public DocumentationInterface createDocOFU(String name){
		return new DocumentationDecoratorOFU(new Documentation(name));
	}
	
	public DocumentationInterface createDocIU(String name){
		return new Documentation();
	}
	
	public DocumentationInterface createDocIW(String name){
		return new Documentation();
	}
}
