package com.music.adapter;

import com.music.model.*;
import com.music.observers.*;

public class HeartAdapter implements BeatModelInterface {
	
	HeartModelInterface heart;
	
	public HeartAdapter(HeartModelInterface heart){
		System.out.println("Adapter: constructor");
		this.heart = heart;
	}
	
	public void initialize(){}
	
	public void on(){}
	
	public void off(){}
	
	public int getBPM(){
		return heart.getHeartRate();
	}
	
	public void setBPM(int bpm){}
	
	public void registerObserver(BeatObserver o){
		heart.registerObserver(o);
	}
	
	public void registerObserver(BPMObserver o){
		heart.registerObserver(o);
	}	
	
	public void removeObserver(BeatObserver o){
		heart.removeObserver(o);
	}
	
	public void removeObserver(BPMObserver o){
		heart.removeObserver(o);
	}
	

}
