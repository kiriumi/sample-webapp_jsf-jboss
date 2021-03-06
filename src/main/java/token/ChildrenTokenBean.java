package token;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ChildrenTokenBean implements Serializable{

    @Inject
    private TokenHolder tokenHolder;

    @Inject
    private ExternalContext externalContext;

    @Getter
    @Setter
    private String namespace;

    @Getter
    @Setter
    private String token;

    @Setter
    private String parentToken;

    public void verify(boolean doCheck) throws InvalidTokenException {

        String tokenInRequest = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_TOKEN);
        String namespaceInRequest = (String) externalContext.getRequestParameterMap()
                .get(TokenHolder.REQ_PARAM_TOKEN_NAMESPACE);
        String dialog = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_PF_DIALOG);

        if (!doCheck) {
            updateChildToken(namespaceInRequest);
            return;
        }

        if (StringUtils.isBlank(namespaceInRequest)) {

            // 子画面が開かれた場合
            if (StringUtils.isBlank(dialog) && !tokenHolder.verifyParentToken(tokenInRequest)) { // ダイアログの場合
                throw new InvalidTokenException();
            }
            beginChildToken();

        } else {
            if (!tokenHolder.verifyChildToken(namespaceInRequest, tokenInRequest)) {
                throw new InvalidTokenException();
            }
            updateChildToken(namespaceInRequest);
        }
    }

    public String addTokenParams(Object result) {
        return result + String.join("&",
                TokenHolder.REQ_PARAM_TOKEN_NAMESPACE + "=" + namespace,
                TokenHolder.REQ_PARAM_TOKEN + "=" + token);
    }

    public String getParentToken() {
        // 親画面のトークンを必ず返すため（@GetterにするとNullを返してしまう）
        return tokenHolder.getParentToken();
    }

    private void beginChildToken() {
        this.namespace = tokenHolder.generateNamespace();
        this.token = tokenHolder.updateChildToken(namespace);
    }

    private void updateChildToken(String namespace) {
        this.namespace = namespace;
        this.token = tokenHolder.updateChildToken(namespace);
    }

}
