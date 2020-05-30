package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import context.SystemDirContext;
import context.WebApplicationContext;
import domain.MessageService;
import log.ActionLogging;
import log.ApplicationLogger;
import lombok.Getter;
import security.AvailableTime;

/**
 * 基底画面クラス
 * @author kengo
 *
 */
@ActionLogging
@AvailableTime
public abstract class BaseBackingBean {

    @Inject
    @Getter
    private FacesContext facesContext;

    @Inject
    @Getter
    private ExternalContext externalContext;

    @Inject
    @Getter
    private SystemDirContext systemDirContext;

    @Getter
    @Inject
    private MessageService messageService;

    @Getter
    @Inject
    private ApplicationLogger logger;

    @Inject
    private WebApplicationContext appContext;

    protected Flash getFlash() {
        return externalContext.getFlash();
    }

    protected String redirect(final String pageName) {
        return appContext.redirect(pageName);
    }

}
