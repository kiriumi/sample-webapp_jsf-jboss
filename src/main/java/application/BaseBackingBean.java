package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import context.WebApplicationContext;
import domain.MessageService;
import log.ActionLogging;
import log.ApplicationLogger;
import lombok.Getter;
import security.AvailableTime;

@ActionLogging
@AvailableTime
public abstract class BaseBackingBean {

    @Inject
    private ExternalContext externalContext;

    @Getter
    @Inject
    private ApplicationLogger logger;

    @Inject
    private WebApplicationContext appContext;

    @Getter
    @Inject
    private MessageService messageService;

    /**
     * Flashを取得する
     * ※ 画面遷移の１度きりだけ有効なキー：値マップ
     *
     * @return
     */
    protected Flash getFlash() {
        return externalContext.getFlash();
    }

    protected String redirect(final String pageName) {
        return appContext.redirect(pageName);
    }

}
