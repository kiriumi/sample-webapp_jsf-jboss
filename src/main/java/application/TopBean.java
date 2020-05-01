package application;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

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
public class TopBean extends BaseBackingBean {

    @Inject
    SecurityContext securityContext;

    private String name;

    @PostConstruct
    public void postConstruct() {

        String loginSuccessMessage = (String) getFlash().get("loginSccessMessage");
        setInfoMessage(loginSuccessMessage);

        Principal principal = securityContext.getCallerPrincipal();

        securityContext.isCallerInRole("admin");

        this.name = principal.getName();
    }

    public String goSignupPage() throws Exception {
        return redirect("signup");
    }

}
