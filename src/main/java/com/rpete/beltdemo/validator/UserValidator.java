package com.rpete.beltdemo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.rpete.beltdemo.models.User;
import com.rpete.beltdemo.services.UserService;


@Component
public class UserValidator implements Validator{
	private final UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	// 1
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	// 2
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			// 3
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		if (!userService.findByEmail(user.getEmail()).equals(null)) {
			errors.rejectValue("email", "Unique");
		}
	}
	
	
}
