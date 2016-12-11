package com.ebitik.todo.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ebitik.todo.bean.UserForm;
import com.ebitik.todo.config.TodoException;
import com.ebitik.todo.domain.User;
import com.ebitik.todo.service.UserService;

/**
 * 
 * User controller, using for user register
 * 
 * @author erdal.bitik
 * */

@Controller
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService service;

	@GetMapping("/register")
	String getRegister(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}

	@PostMapping("/register")
	String register(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, RedirectAttributes ra) {
		try {
			//return if any validation error found
			if(result.hasErrors()) {
				return "register";
			}
			User user = userForm.getAsUser();
			service.addUser(user);
			ra.addFlashAttribute("success", "message.register.successfull");
			return "redirect:/login";
		} catch (Exception e) {
			result.addError(new ObjectError("global", e.getClass().getSimpleName()));
			if(!(e instanceof TodoException)) {
				logger.error(e);
			}
		}
		return "register";
	}

}