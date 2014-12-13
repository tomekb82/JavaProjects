package org.example

class Book {
    
    String title;

    static belongsTo = [author: Author]

//    static searchable = {
  //  	author component: true
  //  }

    static constraints = {
    	title blank: false
    }

    String toString(){ title }
}
