package security.custom;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import dto.User;
import lombok.Getter;
import lombok.Setter;

@Named // これがないと認証時に設定した情報が、リスナーで取得できない
@SessionScoped
public class CustomPrincipal implements Serializable {

    private static final String SESSION_KEY_LOGINED = "logined";

    @Getter
    @Setter
    private User user;

    @Inject
    ExternalContext externalContext;

    public boolean hasRole(final String role) {
        return user.getRoles().contains(role);
    }

    public void setLogined(boolean logined) {
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.setAttribute(SESSION_KEY_LOGINED, logined);
    }

    public boolean isLogined() {

        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session == null) {
            return false;
        }

        Boolean logined = (Boolean) session.getAttribute(SESSION_KEY_LOGINED);

        if (logined == null || !logined) {
            return false;
        }

        return true;
    }

}
