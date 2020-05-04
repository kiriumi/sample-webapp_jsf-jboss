package application;

import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import domain.FacesMessageManager;
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

    @Getter
    @Inject
    private FacesMessageManager messageManager;

    /**
     * Flashを取得する
     * ※ 画面遷移後に１度だけ値を参照できる
     *
     * @return
     */
    protected Flash getFlash() {
        return externalContext.getFlash();
    }

    protected String redirect(final String pageName) {
        return String.join("", "/application/", pageName, ".xhtml", "?faces-redirect=true");
    }

}
