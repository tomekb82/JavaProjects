package com.tutorialspoint;

public class TextEditor_SetterBased {

private SpellChecker spellChecker;
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
	
	// a setter method to inject the dependency.
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker." );
		this.spellChecker = spellChecker;
	}
	
	// a getter method to return spellChecker
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	
}
