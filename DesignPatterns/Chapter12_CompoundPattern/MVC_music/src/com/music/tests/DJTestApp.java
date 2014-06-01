package com.music.tests;

import com.music.model.*;
import com.music.controller.*;

public class DJTestApp {

	public static void main(String[] args){
		
		/* First create MODEL */
		BeatModelInterface model = new BeatModel();
		/* Then create Controller
		 * and pass it the MODEL 
		 * Controller creates the VIEW, so we don't have to do it.
		 */
		ControllerInterface controller = new BeatController(model);
	}
}
