package context;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
public class WebApplicationContext implements Serializable {

    @Getter
    private final String baseApplicationPagePath = "/application/";

    private final String redirectParam = "?faces-redirect=true";

    @Getter
    private final String systemErrorPagePath = baseApplicationPagePath + "error-system.xhtml";

    public String redirect(final String pageName) {
        return String.join("", baseApplicationPagePath, pageName, redirectParam);
    }

    public String redirectLoginPage() {
        return String.join("", "login", redirectParam);
    }

    public String redirectSignup() {
        return String.join("", "signup", redirectParam);
    }

    public String redirectTwoFactorAuthPage() {
        return String.join("", "two-factor-auth", redirectParam);
    }

    public String redirectSystemErrorPage() {
        return String.join("", systemErrorPagePath, redirectParam);
    }
}
