package domain;

import javax.enterprise.context.SessionScoped;

import dto.User;
import lombok.Getter;

@SessionScoped
public class Principal {

    @Getter
    private final String emailAddress;

    @Getter
    private final String name;

    @Getter
    private final String password;

    public Principal(final User user) {
        this.emailAddress = user.getEmailaddress();
        this.name = user.getName();
        this.password = user.getPassword();
    }

}
