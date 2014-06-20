package eu.tbelina.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.tbelina.spring.model.UserForm;

@Controller
public class LoginController {

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model) {
        UserForm user = new UserForm();
        model.put("userForm", user);
        return "LoginForm";
    }
 
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("userForm") UserForm userForm,
            BindingResult result, Map<String, Object> model) {
 
        if (result.hasErrors()) {
            return "LoginForm";
        }
 
        return "LoginSuccess";
    }
    
}
