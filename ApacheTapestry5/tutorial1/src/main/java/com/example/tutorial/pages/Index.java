package com.example.tutorial.pages;

import java.util.Date;
import java.util.Random;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;

/**
 * Start page of application tutorial1.
 */
public class Index
{
	private final Random random = new Random(System.nanoTime());
	 
    @InjectPage
    private Guess guess;
    
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;

    public Date getCurrentTime()
    {
        return new Date();
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }
    
    @Log
    Object onActionFromStart()
    {
    	int target = random.nextInt(10) + 1;
    	 
        guess.setup(target);
        return guess;
    }
}
