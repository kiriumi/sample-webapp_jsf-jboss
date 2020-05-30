package context;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;

@ApplicationScoped
@Getter
public class MyApplicationContext implements Serializable {

    @Inject
    ExternalContext externalContext;

    private final String baseApplicationPagePath = "/application/";

    private final String systemErrorPagePath = baseApplicationPagePath + "error-system.xhtml";

    public String redirect(final String pageName) {
        return String.join("", baseApplicationPagePath, pageName, "?faces-redirect=true");
    }

    public String redirectSystemErrorPage() {
        return String.join("", systemErrorPagePath, "?faces-redirect=true");
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) externalContext.getRequest();
    }

    public HttpServletRequest getResponse() {
        return (HttpServletRequest) externalContext.getResponse();
    }
}
