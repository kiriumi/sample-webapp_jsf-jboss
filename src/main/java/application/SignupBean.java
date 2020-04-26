package application;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import dto.User;
import interceptor.TestModeTransactional;
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
    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    private String password;

    @Inject
    private UserService userService;

    //    @Transactional
    @TestModeTransactional
    public String signupUser() {

        if (userService.hasUser(getEmailAddress())) {
            return null;
        }

        userService.addUser(createUser());

        getFlash().put("signupSccessMessage", "登録できたよ");

        return "login.xhtml";
    }

    //    @Transactional
    @TestModeTransactional
    public String signupUserWithJpa() {

        if (userService.hasUserWithJpa(getEmailAddress())) {
            return null;
        }

        userService.addUserWithJpa(createUser());

        getFlash().put("signupSccessMessage", "登録できたよ");

        return "login.xhtml";
    }

    private User createUser() {

        User user = new User();
        user.setEmailaddress(getEmailAddress());
        user.setName(getName());
        user.setPassword(getPassword());

        LocalDateTime dateTime = LocalDateTime.now();
        user.setCreatedtime(dateTime.toString());
        user.setUpdatedtime(dateTime.toString());

        return user;
    }

}
