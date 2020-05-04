package domain;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class FacesMessageManager {

    @Inject
    private FacesContext facesContext;

    public void setMessage(final Severity severity, @NotBlank final String message) {
        facesContext.addMessage(null, new FacesMessage(severity, message, null));
        log(severity, message);
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

        log(severity, message);
    }

    private void log(final Severity severity, final String message) {

        if (severity.equals(FacesMessage.SEVERITY_INFO)) {
            log.info(message);
        } else if (severity.equals(FacesMessage.SEVERITY_WARN)) {
            log.warn(message);
        } else {
            log.error(message);
        }
    }

}
