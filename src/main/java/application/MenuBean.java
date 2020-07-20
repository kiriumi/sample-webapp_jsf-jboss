package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.ServletException;

import page_transaction.PageTransactionBegin;

@Model
public class MenuBean extends BaseBackingBean {

    @Inject
    ExternalContext externalContext;

    @Inject
    private CommonBean commonBean;

    @PageTransactionBegin("signup")
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

    public String logout() throws ServletException {
        commonBean.logout();
        return "/login.xhtml?faces-redirect=true";
    }
}
