package context;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
@Getter
public class MyApplicationContext implements Serializable {

    private final String baseApplicationPagePath = "/application/";

    private final String systemErrorPagePath = baseApplicationPagePath + "error-system.xhtml";

    public String redirect(final String pageName) {
        return String.join("", baseApplicationPagePath, pageName, "?faces-redirect=true");
    }

    public String redirectSystemErrorPage() {
        return String.join("", systemErrorPagePath, "?faces-redirect=true");
    }
}
