package org.example

import grails.converters.JSON
import grails.converters.XML

class BookController {
    static scaffold = true

	
	def hello(){
		render "Hello world"
	}

	def apiList(){
		def books = Book.list()
		withFormat{
			json{
				render books as JSON
			}
			xml{
				render books as XML
			}
		}	
	}
}
