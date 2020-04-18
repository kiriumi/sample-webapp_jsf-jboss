package validation;

import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import domain.UserService;

@Dependent
@FacesValidator("authValidator")
public class AuthJsfValidator implements Validator<Object> {

	@Inject
	private UserService userService;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException {

		UIInput uiInputEmailAddress = (UIInput) component.findComponent("emailAddress");
		String emailAddress = uiInputEmailAddress.getLocalValue() == null ? ""
				: uiInputEmailAddress.getLocalValue().toString();

		UIInput uiInputPassword = (UIInput) component.findComponent("password");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString();

		if (!userService.find(emailAddress, password)) {

			ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");
			String errorEessage = resourceBundle.getString("error.message.login");

			FacesMessage message = new FacesMessage(errorEessage);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(component.getClientId(), message);
			context.renderResponse();
		}
	}

}
