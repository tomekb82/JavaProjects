package com.music.adapter;

import com.music.observers.*;

public interface HeartModelInterface {

	int getHeartRate();
	
	void registerObserver(BeatObserver o);
	void registerObserver(BPMObserver o);
	void removeObserver(BeatObserver o);
	void removeObserver(BPMObserver o);
}
