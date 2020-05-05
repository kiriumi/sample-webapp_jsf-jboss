package security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.soteria.cdi.CdiUtils;
import org.glassfish.soteria.mechanisms.LoginToContinueHolder;

@AutoApplySession
@LoginToContinue(loginPage = "/login.xhtml", errorPage = "/login.xhtml")
@ApplicationScoped
public class MyHttpAuthenticationMechanism implements HttpAuthenticationMechanism, LoginToContinueHolder {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    private LoginToContinue loginToContinue;

    private final String loginPage = "/login.xhtml";

    // リクエストの度に動作することは確認済み
    // ログイン画面へのリダイレクトがめっちゃしんどいため、中断

    @Override
    public AuthenticationStatus validateRequest(final HttpServletRequest request, final HttpServletResponse response,
            final HttpMessageContext httpMessageContext) {

        String requestedPage = request.getServletPath();
        if (requestedPage.equals(loginPage) || requestedPage.startsWith("/javax.faces.resource")) {
            return httpMessageContext.doNothing();
        }

        if (hasCredential(httpMessageContext)) {

            IdentityStoreHandler identityStoreHandler = CdiUtils.getBeanReference(IdentityStoreHandler.class);

            return httpMessageContext.notifyContainerAboutLogin(
                    identityStoreHandler.validate(
                            httpMessageContext.getAuthParameters()
                                    .getCredential()));
        }

        return httpMessageContext.redirect(request.getContextPath() + loginPage);
    }

    private static boolean hasCredential(final HttpMessageContext httpMessageContext) {
        return httpMessageContext.getAuthParameters().getCredential() != null;
    }

    @Override
    public LoginToContinue getLoginToContinue() {
        return loginToContinue;
    }

    public void setLoginToContinue(final LoginToContinue loginToContinue) {
        this.loginToContinue = loginToContinue;
    }

}
