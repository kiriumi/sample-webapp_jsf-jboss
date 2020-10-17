package token;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.enterprise.context.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class TokenHolder implements Serializable {

    public static final String REQ_PARAM_ID_TOKEN = "token";

    public static final String REQ_PARAM_ID_TOKEN_NAMESPACE = "token-namespace";

    public static final String REQ_PARAM_TOKEN_NAMESPACE = "tokenNamespace";

    public static final String REQ_PARAM_OPENED_WINDOW = "openedWindow";

    public static final String REQ_PARAM_CLOSED_CHILD_WINDOW = "closedChildWindow";

    @Getter
    @Setter
    private String parentToken;

    private Map<String, String> childrenToken = new HashMap<>();

    public String updateParentToken() {
        this.parentToken = Integer.toString(new Random().nextInt(100));
        return parentToken;
    }

    public boolean validParentToken(String token) {

        if (StringUtils.isBlank(token)) {
            return false;
        }

        if (token.equals(parentToken)) {
            return true;
        }
        return false;
    }

    public String createNamespace() {
        return Integer.toString(new Random().nextInt(10000));
    }

    public String updateChildToken(String namespace) {
        String token = Integer.toString(new Random().nextInt(100));
        setChildToken(namespace, token);
        return token;
    }

    public String getChildToken(String namespace) {
        return childrenToken.get(namespace);
    }

    public void setChildToken(String namespace, String token) {
        childrenToken.put(namespace, token);
    }

    public boolean validChildToken(String namespace, String token) {

        if (StringUtils.isBlank(namespace)) {
            return false;
        }

        if (StringUtils.isBlank(token)) {
            return false;
        }

        if (token.equals(childrenToken.get(namespace))) {
            return true;
        }
        return false;
    }

    public void clearChildrenToken() {
        childrenToken.clear();
    }

    public boolean isEmptyChildrenToken() {
        return childrenToken.isEmpty();
    }
}
