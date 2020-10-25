package token;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class TokenBean implements Serializable {

    @Inject
    private TokenHolder tokenHolder;

    @Inject
    ExternalContext externalContext;

    @Getter
    @Setter
    private String token;

    public void verify(boolean doCheck) throws InvalidTokenException {

        if (!doCheck) {
            this.token = tokenHolder.updateParentToken();
            return;
        }

        String tokenInRequest = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_TOKEN);
        if (!tokenHolder.verifyParentToken(tokenInRequest)) {
            throw new InvalidTokenException();
        }
        this.token = tokenHolder.updateParentToken();
    }

    public String addTokenParams(Object pageName) {
        tokenHolder.clearChildrenToken();
        return String.join("&", (String) pageName, TokenHolder.REQ_PARAM_TOKEN + "=" + token);
    }

}
