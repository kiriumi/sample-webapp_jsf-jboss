package context;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@ApplicationScoped
public class WebApplicationContext implements Serializable {

    @Inject
    private ExternalContext externalContext;

    @Getter
    private final String baseApplicationPagePath = "/application/";

    private final String redirectParam = "?faces-redirect=true";

    public String redirect(final String pageName) {
        return StringUtils.join(baseApplicationPagePath, pageName, redirectParam);
    }

    public String redirectNonSecuredPage(final String pageName) {
        return StringUtils.join(pageName, redirectParam);
    }

    public String redirectSystemErrorPage() {
        return StringUtils.join("error-system.xhtml", redirectParam);
    }

    public HttpServletRequest request() {
        return (HttpServletRequest) externalContext.getRequest();
    }

    public HttpServletResponse response() {
        return (HttpServletResponse) externalContext.getResponse();
    }

    public Principal getUserPrincipal() {
        return request().getUserPrincipal();
    }

    public boolean isUserInRole(final String role) {
        return request().isUserInRole(role);
    }

}
