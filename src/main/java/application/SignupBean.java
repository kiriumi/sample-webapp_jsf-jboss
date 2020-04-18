package application;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
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
import repos.UserRepository;

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
	@Size(min = 4, max = 8)
	@Pattern(regexp = "[0-9a-zA-Z]*")
	private String password;

	@Inject
	private UserService userService;

	@Inject
	private UserRepository userRepository;

	@Transactional
	public String signupUser() {

		if (userService.hasUser(getEmailAddress())) {
			return null;
		}

		User user = new User();
		user.setEmailaddress(getEmailAddress());
		user.setName(getName());
		user.setPassword(getPassword());

		LocalDateTime dateTime = LocalDateTime.now();
		user.setCreatedtime(dateTime.toString());
		user.setUpdatedtime(dateTime.toString());

		userService.addUser(user);

		getFlash().put("signupSccessMessage", "登録できたよ");

		return "login.xhtml";
	}

	@Transactional
	public String signupUserWithJpa() {

		if (userRepository.hasUser(getEmailAddress())) {
			return null;
		}

		model.UserEntity user = new model.UserEntity();
		user.setEmailAddress(getEmailAddress());
		user.setName(getName());
		user.setPassword(getPassword());

		userRepository.addUser(user);

		return null;
	}
}
