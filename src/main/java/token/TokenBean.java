package token;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

@Model
public class TokenBean {

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
//            this.token = tokenHolder.getParentToken();
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
        return pageName + String.join("&", TokenHolder.REQ_PARAM_TOKEN + "=" + token);
    }

}
