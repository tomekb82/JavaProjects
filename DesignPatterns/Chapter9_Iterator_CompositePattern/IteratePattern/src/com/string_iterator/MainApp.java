package com.string_iterator;

public class MainApp {

	public static void main(String[] args) {
	    // create an array of type Integer
	    Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};

	    // create your list and hold the values.
	    SOList<Integer> stackOverflowList = new SOList<Integer>(numbers);

	    // Since our class SOList is an instance of Iterable, then we can use it on a foreach loop
	    for(Integer num : stackOverflowList) {
	        System.out.print(num);
	    }

	    // creating an array of Strings
	    String[] languages = new String[]{"C", "C++", "Java", "Python", "Scala"};

	    // create your list and hold the values using the same list implementation.
	    SOList<String> languagesList = new SOList<String>(languages);

	    System.out.println("");
	    // Since our class SOList is an instance of Iterable, then we can use it on a foreach loop
	    for(String lang : languagesList) {
	        System.out.println(lang);
	    }
	}
}
