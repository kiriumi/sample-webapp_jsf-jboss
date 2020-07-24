package application;

import java.time.LocalDateTime;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import dto.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import mode.TestModeTransactional;
import page_transaction.PageTransactionEnd;

/**
 * ユーザ登録画面クラス
 *
 * @author Kengo
 *
 */
@Model
@EqualsAndHashCode(callSuper = false)
public class SignupBean extends BaseBackingBean {

    @NotBlank
    @Email
    @Getter
    @Setter
    private String emailAddress;

    @NotBlank
    @Getter
    @Setter
    private String name;

    @NotBlank
    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    @Getter
    @Setter
    private String password;

    @Inject
    private UserService userService;

    @TestModeTransactional
    @PageTransactionEnd
    public String signupUser() {

        if (userService.hasUser(getEmailAddress())) {
            return null;
        }

        userService.addUser(createUser());

        getFlash().put("signupSccessMessage", "登録できたよ");

        return redirect("top");
    }

    @TestModeTransactional
    public String signupUserWithJpa() {

        if (userService.hasUserWithJpa(getEmailAddress())) {
            return null;
        }

        userService.addUserWithJpa(createUser());

        getFlash().put("signupSccessMessage", "登録できたよ");

        return redirect("signup");
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

    public String back() {
        return redirect("login");
    }

}
