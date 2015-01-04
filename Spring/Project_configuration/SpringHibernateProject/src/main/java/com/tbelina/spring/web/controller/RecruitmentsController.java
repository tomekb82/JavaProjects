package com.tbelina.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbelina.spring.dao.RecruitmentDao;
import com.tbelina.spring.model.Recruitment;
import com.tbelina.spring.notifications.NotificationsService;


@Controller
@RequestMapping({"/", "/recruitments"})
public class RecruitmentsController {

	@Autowired
	protected NotificationsService notificationsService;
	
	@Autowired
	protected RecruitmentDao recruitmentsDao;

	
	@RequestMapping({"", "/", "/list"})
	public String list(Model model) {
		List<Recruitment> recruitments = recruitmentsDao.getList();
		model.addAttribute("recruitments", recruitments);
		return "recruitments.list";
	}
	
}
