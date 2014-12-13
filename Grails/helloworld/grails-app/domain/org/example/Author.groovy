package org.example

class Author {

	String name
	static hasMany = [books: Book]

	//static searchable = {
//		only = ["name"]
//		root false	
//	}

    	static constraints = {
    		name blank: false
	}
}
