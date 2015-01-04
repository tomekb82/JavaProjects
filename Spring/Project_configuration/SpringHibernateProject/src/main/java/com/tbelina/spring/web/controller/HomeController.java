package com.tbelina.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tbelina.spring.dao.UserDao;
import com.tbelina.spring.model.User;
import com.tbelina.spring.notifications.MessageType;
import com.tbelina.spring.notifications.NotificationsService;
import com.tbelina.spring.service.CurrentUserService;

@Controller
@RequestMapping("/profile")
public class HomeController {

	@Autowired
	UserDao usersDao;
	
	@Autowired
	NotificationsService notificationsService;
	
	@Autowired
	CurrentUserService currentUserService;
	
	@RequestMapping(value={"", "/"}, method=RequestMethod.GET)
	public String checkName(Model model) {
		String username = ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = usersDao.findByLogin(username);
		if (user.getFirstName() == null || user.getFirstName().equals("") 
				|| user.getLastName() == null || user.getLastName().equals("")) {
			model.addAttribute("nameFirst", user.getFirstName());
			model.addAttribute("nameLast", user.getLastName());
			return "profile.name";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value={"", "/"}, method=RequestMethod.POST)
	public String changeName(Model model, @RequestParam("name_first") String nameFirst, @RequestParam("name_last") String nameLast) {
		User user = currentUserService.getUser();
		user.setFirstName(nameFirst);
		user.setLastName(nameLast);
		usersDao.update(user);
		currentUserService.invalidateUserDetails();
		
		if (!"".equals(nameFirst) && !"".equals(nameLast)) {
			notificationsService.addMessage("Imię i nazwisko zostały zapisane");
			return "redirect:/";
		} else {
			notificationsService.addMessage("Proszę uzupełnić zarówno imię jak i nazwisko", MessageType.WARNING);
			return "redirect:/profile";
		}
		
		
		
	}
	
}
