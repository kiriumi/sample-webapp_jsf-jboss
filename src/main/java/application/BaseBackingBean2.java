package application;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.WebApplicationContext;
import domain.MessageService;
import log.ApplicationLogger;
import lombok.Getter;
import token.ChildToken;
import token.Token;
import token.TokenUtils;

public class BaseBackingBean2 implements Serializable, BackingBeanInterface {

    @Inject
    @Getter
    private FacesContext facesContext;

    @Inject
    @Getter
    private ExternalContext externalContext;

    @Inject
    @Getter
    private WebApplicationContext appContext;

    @Inject
    private ApplicationLogger logger;

    @Inject
    private MessageService messageService;

    private static final String REDIRECT_PARAM = "?faces-redirect=true";

    protected final HttpServletRequest request() {
        return (HttpServletRequest) externalContext.getRequest();
    }

    protected final HttpServletResponse response() {
        return (HttpServletResponse) externalContext.getResponse();
    }

    protected final Flash flash() {
        return externalContext.getFlash();
    }

    protected ApplicationLogger logger() {
        return logger;
    }

    protected MessageService messageService() {
        return messageService;
    }

    @Inject
    private Token token;

    @Inject
    private ChildToken childToken;

    protected String redirect(final String pageName) {

        if (childToken.getNamespace() == null) {
            // 親画面での画面遷移の場合
            return String.join("", pageName, REDIRECT_PARAM, "&", TokenUtils.KEY_TOKEN, "=", token.getToken());
        }

        // 子画面での画面遷移の場合
        return String.join("", pageName, REDIRECT_PARAM,
                "&", TokenUtils.KEY_CHILD_TOKEN_NAMESPACE, "=", childToken.getNamespace(),
                "&", TokenUtils.KEY_CHILD_TOKEN, "=", childToken.getToken());
    }

}
