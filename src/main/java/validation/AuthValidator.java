package validation;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import domain.UserService;

public class AuthValidator implements ConstraintValidator<Auth, Object> {

	private Auth annotation;

	@Inject
	private UserService userService;

	@Override
	public void initialize(final Auth constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(final Object object, final ConstraintValidatorContext context) {

		String emailAddress = "";
		String password = "";

		try {

			Field emailAddressField = object.getClass().getDeclaredField(annotation.emailAddress());
			emailAddressField.setAccessible(true);
			emailAddress = (String) emailAddressField.get(object);

			Field passwordField = object.getClass().getDeclaredField(annotation.password());
			passwordField.setAccessible(true);
			password = (String) passwordField.get(object);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return userService.find(emailAddress, password);
	}
}