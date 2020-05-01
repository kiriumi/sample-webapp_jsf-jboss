package application;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import log.ActionLogging;
import log.ApplicationLogger;
import lombok.Getter;

@ActionLogging
public abstract class BaseBackingBean {

    @Inject
    private FacesContext facesContext;

    @Getter
    @Inject
    private ApplicationLogger logger;

    /**
     * Flashを取得する
     * ※ 画面遷移後に１度だけ値を参照できる
     *
     * @return
     */
    protected Flash getFlash() {
        return facesContext.getExternalContext().getFlash();
    }

    protected void setInfoMessage(final String messageId) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application-messages");
        String message = resourceBundle.getString(messageId);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    protected void setWarnMessage(final String messageId) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application-messages");
        String message = resourceBundle.getString(messageId);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    protected void setErrorMessage(final String messageId) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");
        String message = resourceBundle.getString(messageId);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected String redirect(final String pageName) {
        return String.join("", pageName, "?faces-redirect=true");
    }

}
