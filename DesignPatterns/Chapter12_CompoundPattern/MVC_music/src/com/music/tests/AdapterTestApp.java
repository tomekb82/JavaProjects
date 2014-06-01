package com.music.tests;

import com.music.adapter.*;
import com.music.controller.*;

public class AdapterTestApp {

	public static void main(String[] args){
		
		HeartModelInterface heartModel = new HeartModel();
		ControllerInterface model = new HeartController(heartModel);
		
	}
}
