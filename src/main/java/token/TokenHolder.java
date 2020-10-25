package token;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class TokenHolder implements Serializable {

    public static final String REQ_PARAM_TOKEN = "token";

    public static final String REQ_PARAM_TOKEN_NAMESPACE = "tokenNamespace";

    @Inject
    ExternalContext externalContext;

    @Getter
    @Setter
    private String parentToken;

    private Map<String, String> childrenToken = new HashMap<>();

    public String updateParentToken() {
        this.parentToken = updateToken();
        return parentToken;
    }

    public boolean verifyParentToken(String token) {

        if (StringUtils.isBlank(token)) {
            return false;
        }

        if (token.equals(parentToken)) {
            return true;
        }
        return false;
    }

    public String generateNamespace() {

        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = secureRandom.generateSeed(LocalDate.now().hashCode());

        return DigestUtils.md5Hex(seed).substring(0, 8).toUpperCase();
    }

    public String getChildToken(String namespace) {
        return childrenToken.get(namespace);
    }

    public String updateChildToken(String namespace) {
        String token = updateToken();
        childrenToken.put(namespace, token);
        return token;
    }

    public boolean verifyChildToken(String namespace, String token) {

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

    private String updateToken() {

        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = secureRandom.generateSeed(LocalDate.now().hashCode());

        return DigestUtils.sha512Hex(seed).substring(0, 64);
    }

}
