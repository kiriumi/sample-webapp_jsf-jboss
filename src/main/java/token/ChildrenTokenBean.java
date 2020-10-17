package token;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ChildrenTokenBean implements Serializable {

    @Getter
    @Setter
    private String namespace;

    @Getter
    @Setter
    private String token;

    @Setter
    private String parentToken;

    @Inject
    private TokenHolder tokenHolder;

    @Inject
    ExternalContext externalContext;

    @PostConstruct
    public void init() throws InvalidTokenException {

        String tokenInRequest = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_ID_TOKEN);
        String namespaceInRequest = (String) externalContext.getRequestParameterMap()
                .get(TokenHolder.REQ_PARAM_TOKEN_NAMESPACE);

        if (openedWindow()) {

            if (!tokenHolder.validParentToken(tokenInRequest)) {
                throw new InvalidTokenException();
            }
            beginChildToken();

        } else if (closedChildWindow()) {
            updateChildToken(namespaceInRequest);

        } else {
            if (!tokenHolder.validChildToken(namespaceInRequest, tokenInRequest)) {
                throw new InvalidTokenException();
            }
            updateChildToken(namespaceInRequest);
        }
    }

    public String getParentToken() {
        return tokenHolder.getParentToken();
    }

    public String getCurrentToken() {
        return token;
    }

    private void beginChildToken() {
        this.namespace = tokenHolder.createNamespace();
        this.token = tokenHolder.updateChildToken(namespace);
    }

    private void updateChildToken(String namespace) {
        this.namespace = namespace;
        this.token = tokenHolder.updateChildToken(namespace);
    }

    private boolean openedWindow() {

        String openedWindow = (String) externalContext.getRequestParameterMap()
                .get(TokenHolder.REQ_PARAM_OPENED_WINDOW);

        if (openedWindow != null) {
            return true;
        }
        return false;
    }

    private boolean closedChildWindow() {

        String closesChildWindow = (String) externalContext.getRequestParameterMap()
                .get(TokenHolder.REQ_PARAM_CLOSED_CHILD_WINDOW);

        if (closesChildWindow != null) {
            return true;
        }
        return false;
    }

}
