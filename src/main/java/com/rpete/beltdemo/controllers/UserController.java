package com.rpete.beltdemo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import com.rpete.beltdemo.models.User;
import com.rpete.beltdemo.services.UserService;
import com.rpete.beltdemo.validator.UserValidator;


@Controller
public class UserController {
	 private final UserService userService;
	 private final UserValidator userValidator;
	    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    // redirects to registration
    @RequestMapping("/")
    public String index() {
    	return "redirect:/registration";
    }
    
    // returns registration jsp template
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "regPage.jsp";
    }
    
    // returns login jsp template
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    // returns dashboard jsp template
    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
    		// user is not logged in
    		redirectAttributes.addFlashAttribute("success_error", "You must be logged in to continue!");
    		return "redirect:/login";
    	}
    	return "dashboard.jsp";
    }
    
    // method for submitting registration form
    @RequestMapping(value="/submitRegistration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes, BindingResult result, HttpSession session) {
        // if result has errors, return the registration page (don't worry about validations just now)
    	// errors using flashAttributes
//    	if (!user.getPassword().equals(user.getPasswordConfirmation())) {
//    		// if passwords do not match
//    		redirectAttributes.addFlashAttribute("password_error", "Passwords must match!");
//    		return "redirect:/registration";
//    	}
    	// handle error message with validator
    	userValidator.validate(user, result);
    	
    	if (result.hasErrors()) {
    		return "regPage.jsp";
    	} else {
    		// else, save the user in the database, save the user id in session, and redirect them to the /home route
//    		User checkUser = userService.findByEmail(user.getEmail());
//    		if (checkUser == null) {
    			// if yes, create the user
			User saveUser = userService.registerUser(user);
			// save the user id in session
			session.setAttribute("user_id", saveUser.getId());
			return "redirect:/dashboard";
//    		} else {
//    			// if no, send validation errors
//    			redirectAttributes.addFlashAttribute("email_error", "Email must be unique!");
//    			return "redirect:/registration";
//    		}
    	}
    }
    
    @RequestMapping(value="/submitLogin", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    	boolean isValid = userService.authenticateUser(email, password);
    	if (isValid == true) {
    		User validUser = userService.findByEmail(email);
    		session.setAttribute("user_id", validUser.getId());
    		return "redirect:/dashboard";
    	} else {
    		// else, add error messages and return the login page
    		redirectAttributes.addFlashAttribute("login_error", "Invalid email/password");
    		return "redirect:/login";
    	}
    }
//    
//    @RequestMapping("/home")
//    public String home(HttpSession session, Model model) {
//        // get user from session, save them in the model and return the home page
//    	return "";
//    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/login";
    }
}
