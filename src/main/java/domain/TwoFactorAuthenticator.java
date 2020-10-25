package domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;

import org.apache.commons.codec.digest.DigestUtils;

import context.WebApplicationContext;
import dto.User;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class TwoFactorAuthenticator implements Serializable {

    @Inject
    ExternalContext externalContext;

    @Inject
    WebApplicationContext appContext;

    @Inject
    UserService userService;

    @Getter
    private User user;

    private String token;

    @Getter
    private boolean sentToken = false;

    private LocalDateTime expiredTime;

    @Getter
    @Setter
    private boolean firstAuthed = false;

    public boolean firstAuth(final String emailAddress, final String password) {

        this.user = userService.find(emailAddress, password);
        if (user == null) {
            return false;
        }
        firstAuthed = true;

        return true;
    }

    public void sendTokenToUser() {

        LocalDateTime createdTime = LocalDateTime.now();
        this.expiredTime = createdTime.plusMinutes(30);
        this.token = DigestUtils.md5Hex(createdTime.toString()).substring(0, 6);
        this.sentToken = true;
        // メールやSMSでトークンをユーザーに送信
    }

    public boolean expiredToken() {

        if (LocalDateTime.now().isAfter(expiredTime)) {
            clear();
            return true;
        }

        return false;
    }

    public boolean secondAuth(final String inputtedToken) throws AuthenticationException {

//    	if (inputtedToken.equals(token)) {
//			return true;
//		}
//    	return false;

        return true;
    }

    public boolean logined() {
        return externalContext.getUserPrincipal() != null;
    }

    public void clear() {
        this.user = null;
        this.token = null;
        this.sentToken = false;
        this.expiredTime = null;
        this.firstAuthed = false;
    }

}
