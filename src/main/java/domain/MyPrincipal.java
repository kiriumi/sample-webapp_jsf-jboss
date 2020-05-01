package domain;

import java.security.Principal;

import javax.enterprise.context.SessionScoped;

import dto.User;
import lombok.Getter;

@SessionScoped
public class MyPrincipal implements Principal {

    @Getter
    private final String emailAddress;

    @Getter
    private final String userName;

    @Getter
    private final String password;

    public MyPrincipal(final User user) {
        this.emailAddress = user.getEmailaddress();
        this.userName = user.getName();
        this.password = user.getPassword();
    }

    @Override
    public String getName() {
        return emailAddress;
    }

}
