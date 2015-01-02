package com.tbelina.spring.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tbelina.spring.dao.UserDao;
import com.tbelina.spring.model.User;
import com.tbelina.spring.model.enums.UserRole;
import com.tbelina.spring.notifications.MessageType;
import com.tbelina.spring.notifications.NotificationsService;
import com.tbelina.spring.service.CurrentUserService;
import com.tbelina.spring.service.UsersService;

@Controller
@RequestMapping("/manage/users")
public class UsersManagementController {

	@Autowired
	UserDao usersDao;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	NotificationsService notificationsService;
	
	@RequestMapping({"", "/", "/list"})
	public String list(Model model) {
		List<User> users = usersDao.getList();
		model.addAttribute("users", users);
		return "manage.users.list";
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam("login") String login, 
			@RequestParam("password") String password, 
			@RequestParam("nameFirst") String nameFirst, 
			@RequestParam("nameLast") String nameLast, 
			@RequestParam("role") String role) {
		User user = new User();
		user.setFirstName(nameFirst);
		user.setLastName(nameLast);
		user.setLogin(login);
		user.setPassword(sha1(password));
		user.setCleartextPassword(password);
		user.setActive(true);
		UserRole erole = UserRole.assesor;
		try {
			erole = UserRole.valueOf(role);
		} catch (Exception e) {
			//ignore
		}
		user.setRole(erole);
		usersDao.save(user);
		return "redirect:/manage/users/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer userId) {
		User user = usersDao.findById(userId);
		if (!user.getLogin().equals(currentUserService.getLogin())) {
			if (!user.getRole().equals(UserRole.admin)) {
				usersService.deleteUser(userId);
				notificationsService.addMessage("Użytkownik został usunięty", MessageType.SUCCESS);
			} else {
				notificationsService.addMessage("Nie możesz usunąć administratora", MessageType.ERROR);
			}
		} else {
			notificationsService.addMessage("Nie możesz usunąć sam siebie", MessageType.ERROR);
		}
		return "redirect:/manage/users/list";
	}

	private String sha1(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			byte[] mdbytes = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
}
