package domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import dto.User;
import lombok.Getter;

@SessionScoped
public class TwoFactorAuthenticator implements Serializable {

    @Inject
    ExternalContext externalContext;

    @Inject
    UserService userService;

    private User user;

    private String token;

    @Getter
    private boolean sentToken = false;

    private LocalDateTime expiredTime;

    @Getter
    private boolean firstAuthed = false;

    @Getter
    private boolean secondAuthed = false;

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
            resetToken();
            this.firstAuthed = false;
            return true;
        }

        return false;
    }

    public boolean secondAuth(final String inputtedToken) throws ServletException {

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getUserPrincipal() == null) {
            request.login(user.getEmailaddress(), user.getPassword());
        }
        return true;

        //        if (StringUtils.isBlank(inputtedToken)) {
        //            return false;
        //        }
        //
        //        if (token.equals(inputtedToken)) {
        //
        //            resetToken();
        //            this.secondAuthed = true;
        //
        //            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        //            request.login(user.getEmailaddress(), user.getPassword());
        //
        //            return true;
        //        }
        //
        //        return false;
    }

    private void resetToken() {
        this.token = "";
        this.sentToken = false;
    }

    public void clear() {

        this.user = null;
        this.token = null;
        this.sentToken = false;
        this.expiredTime = null;
        this.firstAuthed = false;
        this.secondAuthed = false;
    }
}
