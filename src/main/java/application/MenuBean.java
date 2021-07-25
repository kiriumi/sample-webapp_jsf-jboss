package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;

@Model
public class MenuBean extends BaseBackingBean {

    @Inject
    ExternalContext externalContext;

    @Inject
    private CommonBean commonBean;

    public String goSignupPage() {
        return redirect("signup");
    }

    public String goTopPage() {
        return redirect("top");
    }

    public String goRestClientPage() {
        return redirect("rest-client");
    }

    public String goPrimeFacesSamplesPage() {
        return redirect("primefaces-samples");
    }

    public String goBootstrapSamplesPage() {
        return redirect("bootstrap-samples");
    }

    public String logout() throws AuthenticationException {
        commonBean.logout();
        return "/login.xhtml?faces-redirect=true";
    }

    @Override
    public String redirect(final String pageName) {
        return super.redirect(pageName);
    }
}
