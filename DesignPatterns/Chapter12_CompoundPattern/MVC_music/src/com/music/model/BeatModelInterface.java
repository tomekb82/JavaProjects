package com.music.model;

import com.music.observers.*;

public interface BeatModelInterface {

	void initialize();
	
	/* Controller methods */
	void on();
	void off();
	void setBPM(int bpm);
	int getBPM();

	/* View & Controller: get state and to become observers*/
	/*  - two kinds of observers
	 *  - register objects as observers for state changes
	 */
	void registerObserver(BeatObserver o);
	void removeObserver(BeatObserver o);
	void registerObserver(BPMObserver o);
	void removeObserver(BPMObserver o);

}
