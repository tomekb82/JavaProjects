package com.example.tutorial.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Guess {

	@Property //it is private and there's corresponding property, so Tapestry was able to access it.
	@Persist //mark which fields have values that should persist from one request to the next
	private int target, guessCount;
	
	//persistence strategy is provided by name. 
	//FLASH is a built-in strategy that stores the value in the session, 
	//but only for one request ... 
	//it's designed specifically for these kind of feedback messages. 
	//If you hit F5 in the browser, to refresh, the page will render but 
	//the message will disappear.
	@Property
	@Persist(PersistenceConstants.FLASH)
	private String message;
	 
	@InjectPage
	private GameOver gameOver;
			
	@Property
    private int current;
	
    void setup(int target)
    {
        this.target = target;
        guessCount = 1;
    }
    
    Object onActionFromMakeGuess(int value)
    {
    	if (value == target)
        {
            gameOver.setup(target, guessCount);
            return gameOver;
        }
     
        guessCount++;
     
        message = String.format("Your guess of %d is too %s.", value,
            value < target ? "low" : "high");
     
        return null;
    }
    
}
