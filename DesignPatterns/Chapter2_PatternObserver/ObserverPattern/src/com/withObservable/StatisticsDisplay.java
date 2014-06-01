package com.withObservable;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class StatisticsDisplay implements Observer, DisplayElement{
		
		Observable observable;
	    private ArrayList temperatures;
	    private float temperature;
		private float avg_temp;
		private float max_temp;
		private float min_temp;
				
		public StatisticsDisplay(Observable observable){
			this.observable = observable;
			observable.addObserver(this);			
			temperatures = new ArrayList();
		}
		
		public void update(Observable obs, Object arg){
			
			if(obs instanceof WeatherData){
				
				WeatherData weatherData = (WeatherData)obs;
				this.temperature = weatherData.getTemperature();
				
				temperatures.add(weatherData.getTemperature());  // to nie działa:(
				
				if(temperatures.size()==1){
					max_temp = (float)temperatures.get(0);
					min_temp = (float)temperatures.get(0);
					avg_temp = (float)temperatures.get(0);
				}
				else {
					for (int i = 0; i<temperatures.size(); i++){
						avg_temp = avg_temp + (float)temperatures.get(i);
						if((float)temperatures.get(i) > max_temp)
							max_temp = (float)temperatures.get(i);
					
						if((float)temperatures.get(i) < min_temp)
							min_temp = (float)temperatures.get(i);
						
					}
					avg_temp = avg_temp/(temperatures.size()+1);
				}
				display(); 
			}
			
		}

		public void display(){
			System.out.println("Avg/Max/Min temperature = " + avg_temp 
					            + "/" + max_temp 
					            + "/" + min_temp);
		}
}


