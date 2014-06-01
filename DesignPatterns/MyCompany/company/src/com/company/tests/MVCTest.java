package com.company.tests;

import com.company.mvc.model.*;
import com.company.mvc.controller.*;

public class MVCTest {

	public static void main(String[] args){
		/* First create MODEL */
		ModelInterface model = new Model();
		/* Then create Controller
		 * and pass it the MODEL 
		 * Controller creates the VIEW, so we don't have to do it.
		 */
		ControllerInterface controller = new Controller(model);
	}
}
