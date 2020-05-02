package application;

import java.security.Principal;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Model
@Data
@EqualsAndHashCode(callSuper = false)
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class TopBean extends BaseBackingBean {

    @Inject
    SecurityContext securityContext;

    private String name;

    private boolean adminRole;

    private boolean userRole;

    private int value1 = 0;

    private int value2 = 0;

    private int sumedValue = 0;

    public String sum() {
        this.sumedValue = value1 + value2;
        return null;
    }

    public void viewAction() {

        String loginSuccessMessage = (String) getFlash().get("loginSccessMessage");
        setInfoMessage(loginSuccessMessage);

        Principal principal = securityContext.getCallerPrincipal();

        if (principal == null) {
            return;
        }

        this.name = principal.getName();
        this.adminRole = securityContext.isCallerInRole("admin");
        this.userRole = securityContext.isCallerInRole("user");
    }

    public String goSignupPage() {
        return redirect("signup");
    }

}
