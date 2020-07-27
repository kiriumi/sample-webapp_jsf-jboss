package application;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;

import context.WebApplicationContext;
import domain.TwoFactorAuthenticator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 二段階認証画面クラス
 *
 * @author Kengo
 *
 */
@Model
@EqualsAndHashCode(callSuper = false)
public class TwoFactorAuthBean extends BaseBackingBean {

    @Inject
    private ExternalContext externalContext;

    @Inject
    private WebApplicationContext appContext;

    @Inject
    private CommonBean commonBean;

    @Inject
    TwoFactorAuthenticator authenticator;

    @Getter
    @Setter
    private String token;

    public String viewAction() {

        if (authenticator.logined()) {
            return appContext.redirect("top");
        }

        if (authenticator.isFirstAuthed()) {

            if (!authenticator.isSentToken()) {
                authenticator.sendTokenToUser();
            }

            return null;
        }

        return appContext.redirectNonSecuredPage("login");
    }

    public String authenticate() throws AuthenticationException {

        if (authenticator.expiredToken()) {
            messageService().setMessage(FacesMessage.SEVERITY_ERROR, "トークンの有効期限が切れたよ");
            return null;
        }

        if (authenticator.secondAuth(token)) {
            return appContext.redirect("top");
        }

        messageService().setMessage(FacesMessage.SEVERITY_ERROR, "認証に失敗したよ");
        return null;
    }

    public String resendToken() {

        authenticator.sendTokenToUser();
        messageService().setMessage(FacesMessage.SEVERITY_INFO, "トークンを再送したよ");
        return null;
    }

    public String backLogin() {
        authenticator.clear();
        return appContext.redirectNonSecuredPage("login");
    }

}
