package com.music.controller;

public interface ControllerInterface {

	/*
	 * All methods the VIEW can call on the CONTROLLER
	 */
	void start();
	void stop();
	void increaseBPM();
	void decreaseBPM();
	void setBPM(int bpm);
}
