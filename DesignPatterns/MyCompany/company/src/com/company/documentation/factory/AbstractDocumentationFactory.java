package com.company.documentation.factory;

import com.company.documentation.*;

public abstract class AbstractDocumentationFactory {

	public abstract DocumentationInterface createDocDK(String name);
	//public abstract DocumentationInterface createDocDK();
	public abstract DocumentationInterface createDocOFU(String name);
	public abstract DocumentationInterface createDocIU(String name);
	public abstract DocumentationInterface createDocIW(String name);
}
