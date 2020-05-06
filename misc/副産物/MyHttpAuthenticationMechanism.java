package security;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.*;
import static javax.xml.bind.DatatypeConverter.*;
import static org.glassfish.soteria.Utils.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.soteria.mechanisms.CustomFormAuthenticationMechanism;

//@BasicAuthenticationMechanismDefinition
@LoginToContinue(loginPage = "/login.xhtml", errorPage = "", useForwardToLogin = false)
@ApplicationScoped
public class MyHttpAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Override
    public AuthenticationStatus validateRequest(final HttpServletRequest request, final HttpServletResponse response,
            final HttpMessageContext httpMessageContext) throws AuthenticationException {

        if (!httpMessageContext.isProtected()) {
            return httpMessageContext.doNothing();
        }

        if (httpMessageContext.getCallerPrincipal() == null && !httpMessageContext.isAuthenticationRequest()) {
            return httpMessageContext.responseUnauthorized();
        }

        if (request.getServletPath().endsWith(".xhtml")) {
            return validateWebAppRequest(request, response, httpMessageContext);
        }

        if (httpMessageContext.isProtected()) {
            validateWebApiReqest(request, response, httpMessageContext);
        }

        return httpMessageContext.responseNotFound();
    }

    private AuthenticationStatus validateWebAppRequest(final HttpServletRequest request,
            final HttpServletResponse response, final HttpMessageContext httpMessageContext)
            throws AuthenticationException {
        LoginToContinue loginToContinue = this.getClass().getAnnotation(LoginToContinue.class);
        CustomFormAuthenticationMechanism customFormMechanism = new CustomFormAuthenticationMechanism();
        customFormMechanism.setLoginToContinue(loginToContinue);

        return customFormMechanism.validateRequest(request, response, httpMessageContext);
    }

    public AuthenticationStatus validateWebApiReqest(final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpMessageContext httpMsgContext) throws AuthenticationException {

        String[] credentials = getCredentials(request);
        if (!isEmpty(credentials)) {

            IdentityStoreHandler identityStoreHandler = CDI.current().select(IdentityStoreHandler.class).get();

            CredentialValidationResult result = identityStoreHandler.validate(
                    new UsernamePasswordCredential(credentials[0], new Password(credentials[1])));

            if (result.getStatus() == VALID) {
                return httpMsgContext.notifyContainerAboutLogin(
                        result.getCallerPrincipal(), result.getCallerGroups());
            }
        }

        return httpMsgContext.responseUnauthorized();
    }

    private String[] getCredentials(final HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        if (!isEmpty(authorizationHeader) && authorizationHeader.startsWith("Basic ")) {
            return new String(parseBase64Binary(authorizationHeader.substring(6))).split(":");
        }

        return null;
    }

}
