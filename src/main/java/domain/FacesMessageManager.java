package domain;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

@RequestScoped
public class FacesMessageManager {

    @Inject
    private FacesContext facesContext;

    public void setMessage(final Severity severity, @NotBlank final String message) {
        facesContext.addMessage(null, new FacesMessage(severity, message, null));
    }

    public void setAppMessageById(final Severity severity, @NotBlank final String messageId, final Object... params) {
        setMessageByProperties("application-messages", severity, messageId, params);
    }

    public void setValidaionMessageById(final Severity severity, @NotBlank final String messageId,
            final Object... params) {
        setMessageByProperties("ValidationMessages", severity, messageId, params);
    }

    private void setMessageByProperties(final String baseName, final Severity severity, final String messageId,
            final Object... params) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");
        String rawMessage = resourceBundle.getString(messageId);
        String message = String.format(rawMessage, params);
        facesContext.addMessage(null, new FacesMessage(severity, message, null));
    }

}
