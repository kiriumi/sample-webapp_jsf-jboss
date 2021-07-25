package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

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
    private ExternalContext externalContext;

    @Inject
    private WebApplicationContext appContext;

    @Inject
    private MessageService messageService;

    @Inject
    private ApplicationLogger logger;

    protected Flash flash() {
        return externalContext.getFlash();
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
