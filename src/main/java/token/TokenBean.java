package token;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@ViewScoped
//@RequestScoped
@Slf4j
public class TokenBean implements Serializable {

    @Inject
    private Token tokenHolder;

    @Getter
    @Setter
    private String namespace;

    @Getter
    @Setter
    private String token;

    @Inject
    ExternalContext externalContext;

    @PostConstruct
    public void init() throws InvalidTokenException {

        if (StringUtils.isBlank(tokenHolder.getToken())) {
            // 初回トークン発行
            updateToken();
            return;
        }

        String token = (String) externalContext.getRequestParameterMap().get("token");

        if (!validToken(token)) {
            throw new InvalidTokenException();
        }

        updateToken();
    }

    public boolean validToken(String token) throws InvalidTokenException {

        token = StringUtils.trimToEmpty(token);
        String tokenInSession = tokenHolder.getToken();

        if (token.equals(tokenInSession)) {
            return true;
        }

        return false;
    }

    public void updateToken() {
        this.token = Integer.toString(new Random().nextInt(100));
        tokenHolder.setToken(token);
    }

    public String getToken() {
        return tokenHolder.getToken();
    }

    @PreDestroy
    public void fin() {

    }

}
