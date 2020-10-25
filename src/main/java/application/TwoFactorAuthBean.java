package application;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.servlet.ServletException;

import context.WebApplicationContext;
import domain.TwoFactorAuthenticator;
import lombok.Getter;
import lombok.Setter;

/**
 * 二段階認証画面クラス
 *
 * @author Kengo
 *
 */
@Model
public class TwoFactorAuthBean extends BaseBackingBean {

    @Inject
    private ExternalContext externalContext;

    @Inject
    private WebApplicationContext appContext;

    @Inject
    TwoFactorAuthenticator authenticator;

    @Getter
    @Setter
    private String token;

    public String viewAction() {

        if (authenticator.logined()) {
            return appContext.redirectAppPage("top");
        }

        if (authenticator.isFirstAuthed()) {
            authenticator.sendTokenToUser();
            return null;
        }

        return appContext.redirect("login");
    }

    public String authenticate() throws AuthenticationException {

        if (authenticator.expiredToken()) {
            messageService().addMessage(FacesMessage.SEVERITY_ERROR, "トークンの有効期限が切れたよ");
            return null;
        }

        if (authenticator.secondAuth(token)) {

            try {
                // セッション管理の不備対策のため、セッションを再生成し、セッションIDを変更する
                externalContext.invalidateSession();
                externalContext.getSessionId(true);

                appContext.request().login(
                        authenticator.getUser().getEmailaddress(), authenticator.getUser().getPassword());

            } catch (ServletException e) {
                throw new AuthenticationException("ログインに失敗したから、もう一度やり直してね");

            } finally {
                authenticator.clear();
            }

            return appContext.redirectAppPage("top");
        }

        messageService().addMessage(FacesMessage.SEVERITY_ERROR, "認証に失敗したよ");
        return null;
    }

    public String resendToken() {

        authenticator.sendTokenToUser();
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "トークンを再送したよ");
        return null;
    }

    public String backLogin() {
        authenticator.clear();
        return appContext.redirect("login");
    }

}
