package com.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

	    //private final TodoService service;
	    
	    @RequestMapping(value = "/todo", method = RequestMethod.GET)
	    public String findAll(Model model) {
	        //List<Todo> models = service.findAll();
	    	List<String> models = new ArrayList<String>();//service.findAll();
	    	models.add("kama");
	    	models.add("filip");
	        model.addAttribute("todos", models);
	        return "todo/list";
	    }
   
}