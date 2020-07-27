package domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.servlet.ServletException;

import org.apache.commons.codec.digest.DigestUtils;

import context.WebApplicationContext;
import dto.User;
import lombok.Getter;

@SessionScoped
public class TwoFactorAuthenticator implements Serializable {

    @Inject
    ExternalContext externalContext;

    @Inject
    WebApplicationContext appContext;

    @Inject
    UserService userService;

    private User user;

    private String token;

    @Getter
    private boolean sentToken = false;

    private LocalDateTime expiredTime;

    @Getter
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

        if (logined()) {
            return true;
        }

        try {
            appContext.request().login(user.getEmailaddress(), user.getPassword());
        } catch (ServletException e) {
            throw new AuthenticationException("ログインに失敗したから、もう一度やり直してね");
        }

        return true;

        //        if (StringUtils.isBlank(inputtedToken)) {
        //            return false;
        //        }
        //
        //        if (token.equals(inputtedToken)) {
        //
        //            try {
        //                appContext.request().login(user.getEmailaddress(), user.getPassword());
        //
        //            } catch (ServletException e) {
        //                throw new AuthenticationException("ログイン認証に失敗したから、やり直してね");
        //
        //            } finally {
        //                clear();
        //            }
        //
        //            return true;
        //        }
        //
        //        return false;
    }

    public boolean logined() {
        return appContext.request().getUserPrincipal() != null;
    }

    public void clear() {

        this.user = null;
        this.token = null;
        this.sentToken = false;
        this.expiredTime = null;
        this.firstAuthed = false;
    }

}
