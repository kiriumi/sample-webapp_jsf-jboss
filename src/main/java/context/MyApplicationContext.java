package context;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
public class MyApplicationContext implements Serializable {

    @Getter
    private final String baseApplicationPagePath = "/application/";

    @Getter
    private final String systemErrorPagePath = baseApplicationPagePath + "error-system.xhtml";

    public String redirect(final String pageName) {
        return String.join("", baseApplicationPagePath, pageName, "?faces-redirect=true");
    }

    public String redirectSystemErrorPage() {
        return String.join("", systemErrorPagePath, "?faces-redirect=true");
    }
}
