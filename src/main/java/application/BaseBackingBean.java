package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import context.EnvContext;
import context.WebApplicationContext;
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
    private EnvContext envContext;

    @Inject
    private WebApplicationContext appContext;

    @Inject
    private MessageService messageService;

    @Inject
    private ApplicationLogger logger;

    protected FacesContext facesContext() {
        return facesContext;
    }

    protected ExternalContext externalContext() {
        return externalContext;
    }

    protected Flash flash() {
        return externalContext.getFlash();
    }

    protected EnvContext envContext() {
        return envContext;
    }

    protected MessageService messageService() {
        return messageService;
    }

    protected ApplicationLogger logger() {
        return logger;
    }

    protected String redirect(final String pageName) {
        return appContext.redirectAppPage(pageName);
    }

}
