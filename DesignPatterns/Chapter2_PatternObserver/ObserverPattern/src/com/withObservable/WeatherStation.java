package com.withObservable;

public class WeatherStation {

	public static void main(String[] agrs) {
		
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionDisplay currentDisplay = 
				new CurrentConditionDisplay(weatherData);
		
		StatisticsDisplay statisticsDisplay = 
				new StatisticsDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 40.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 48.5f);
		
	}
}
