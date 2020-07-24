package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import context.SystemEnvContext;
import context.RedirectContext;
import domain.MessageService;
import log.ActionLogging;
import log.ApplicationLogger;

/**
 * 基底画面クラス
 * @author kengo
 *
 */
@ActionLogging
//@AvailableTime
public abstract class BaseBackingBean {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private SystemEnvContext systemEnvContext;

    @Inject
    private MessageService messageService;

    @Inject
    private ApplicationLogger logger;

    @Inject
    private RedirectContext redirectContext;

    protected FacesContext facesContext() {
        return facesContext;
    }

    protected ExternalContext externalContext() {
        return externalContext;
    }

    protected Flash getFlash() {
        return externalContext.getFlash();
    }

    protected SystemEnvContext systemEnvContext() {
        return systemEnvContext;
    }

    protected MessageService messageService() {
        return messageService;
    }

    protected ApplicationLogger logger() {
        return logger;
    }

    protected String redirect(final String pageName) {
        return redirectContext.redirect(pageName);
    }

}
