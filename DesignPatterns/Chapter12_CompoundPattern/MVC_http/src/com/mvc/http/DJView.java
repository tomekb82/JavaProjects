package com.mvc.http;

import javax.servlet.*;
import com.music.model.BeatModel;

public class DJView extends HttpServlet {

	public void init() throws ServletExcpetion {
		BeatModel beatModel = new BeatModel();
		beatModel.initialize();
		getServletContext().setAttribute("beatModel", beatModel)
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
	}
}
