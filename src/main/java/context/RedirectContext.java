package context;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
public class RedirectContext implements Serializable {

    @Getter
    private final String baseApplicationPagePath = "/application/";

    private final String redirectParam = "?faces-redirect=true";

    private final String systemErrorPagePath = baseApplicationPagePath + "error-system.xhtml";

    public String redirect(final String appPageName) {
        return String.join("", baseApplicationPagePath, appPageName, redirectParam);
    }

    public String redirectNonSecuredPage(final String pageName) {
        return String.join("", pageName, redirectParam);
    }

    public String redirectSystemErrorPage() {
        return String.join("", systemErrorPagePath, redirectParam);
    }

}
