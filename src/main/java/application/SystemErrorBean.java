package application;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import context.WebApplicationContext;

/**
 * システム障害画面
 * @author kengo
 *
 */
@Model
public class SystemErrorBean extends BaseBackingBean {

    @Inject
    private WebApplicationContext appContext;

    public String viewAction() {

        if (messageService().isEmpty()) {
            messageService().addMessage(FacesMessage.SEVERITY_INFO, "システム障害画面ですよ");
        }
        return null;
    }

    public String backPage() {
        return appContext.redirectNonSecuredPage("login");
    }

}
