package com.withoutObservable;

import java.util.ArrayList;

public class StatisticsDisplay implements Observer, DisplayElement{
		
	    private ArrayList temperatures;
		private float avg_temp;
		private float max_temp;
		private float min_temp;
		private Subject weatherData;
				
		public StatisticsDisplay(Subject weatherData){
			this.weatherData = weatherData;
			temperatures = new ArrayList();
			weatherData.registerObserver(this);
		}
		
		public void update(float temperature, float humidity, float pressure){
			
			temperatures.add(temperature);  // to nie działa:(
			
			System.out.println("tt" + temperatures.get(1));
			
			if(temperatures.size()==1){
				max_temp = (float)temperatures.get(1);
				min_temp = (float)temperatures.get(1);
				avg_temp = (float)temperatures.get(1);
			}
			else {
				for (int i = 0; i<temperatures.size(); i++){
					avg_temp = avg_temp + (float)temperatures.get(i);
					if((float)temperatures.get(i) > max_temp)
						max_temp = (float)temperatures.get(i);
				
					if((float)temperatures.get(i) < min_temp)
						min_temp = (float)temperatures.get(i);
					avg_temp = avg_temp/temperatures.size();
				}
			}
			display();
		}

		public void display(){
			System.out.println("Avg/Max/Min temperature = " + avg_temp 
							  + "F degree and " + humidity + "% humidity.");
		}
}


