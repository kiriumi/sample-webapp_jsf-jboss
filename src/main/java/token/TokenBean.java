package token;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class TokenBean implements Serializable {

    @Getter
    @Setter
    private String token;

    @Inject
    private TokenHolder tokenHolder;

    @Inject
    ExternalContext externalContext;

    @PostConstruct
    public void init() throws InvalidTokenException {

        if (StringUtils.isBlank(tokenHolder.getParentToken()) || closedChildWindow()) {
            this.token = tokenHolder.updateParentToken();
            return;
        }

        String tokenInRequest = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_ID_TOKEN);
        if (!tokenHolder.validParentToken(tokenInRequest)) {
            throw new InvalidTokenException();
        }

        this.token = tokenHolder.updateParentToken();
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
