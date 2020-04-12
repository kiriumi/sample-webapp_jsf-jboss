package application;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import dto.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Named
@RequestScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class SignupBean extends AbstractManagedBean {

	@NotBlank
	@Email
	private String emailAddress;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 4, max = 16)
	@Pattern(regexp = "[0-9a-zA-Z]*")
	private String password;

	@Inject
	private UserService userService;

	@Transactional
	public String signupUser() {

		if (userService.exists(emailAddress)) {
			FacesMessage errorMessage = new FacesMessage("そのEmailaアドレスは使われてるよ");
			throw new ValidatorException(errorMessage);
			//			return null;
		}

		User user = new User();
		user.setEmailaddress(emailAddress);
		user.setName(name);
		user.setPassword(password);

		LocalDateTime dateTime = LocalDateTime.now();
		user.setCreatedtime(dateTime.toString());
		user.setUpdatedtime(dateTime.toString());

		userService.addUser(user);

		getFlash().put("signupSccessMessage", "登録できたよ");

		return "login.xhtml";
	}
}
