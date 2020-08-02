package application;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.transaction.Transactional;

import context.WebApplicationContext;
import domain.CodeService;
import domain.UserService;
import dto.Code;
import dto.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import mode.TestModeTransactional;
import page_transaction.PageTransactionBegin;
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

    @Inject
    private WebApplicationContext appContext;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    private String region;

    @Getter
    @Setter
    private String locality;

    @Getter
    @Setter
    private String streetAddress;

    @Getter
    @Setter
    private String extendedAddress;

    @Getter
    @Setter
    private List<Code> roles;

    @Getter
    @Setter
    private List<String> selectedRoles;

    @Inject
    private UserService userService;

    @Inject
    private CodeService codeService;

    @PostConstruct
    public void init() {
        this.roles = codeService.find(Code.Kind.ROLE);
    }

    //    @TestModeTransactional
    @Transactional
    @PageTransactionBegin("signup")
    @PageTransactionEnd
    public String signupUser() {

        if (userService.hasUser(getEmailAddress())) {
            messageService().addMessage(FacesMessage.SEVERITY_WARN, "そのメールアドレスのユーザは登録済みだよ");
            return null;
        }

        userService.addUser(createUser(), selectedRoles);
        flash().put("successSignin", true);

        return appContext.redirectNonSecuredPage("login");
    }

    @TestModeTransactional
    public String signupUserWithJpa() {

        if (userService.hasUserWithJpa(getEmailAddress())) {
            messageService().addMessage(FacesMessage.SEVERITY_WARN, "そのメールアドレスのユーザは登録済みだよ");
            return null;
        }

        userService.addUserWithJpa(createUser(), selectedRoles);
        flash().put("successSignin", true);

        return appContext.redirectNonSecuredPage("login");
    }

    private User createUser() {

        User user = new User();

        user.setEmailaddress(emailAddress);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPassword(password);

        user.setPostalCode(postalCode);
        user.setRegion(region);
        user.setLocality(locality);
        user.setStreetAddress(streetAddress);
        user.setExtendedAddress(extendedAddress);

        user.setVersion(1);
        user.setCreatedtime(LocalDateTime.now());
        user.setUpdatedtime(LocalDateTime.now());

        return user;
    }

    public String backLogin() {
        return appContext.redirectNonSecuredPage("login");
    }

}
