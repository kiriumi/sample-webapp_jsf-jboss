package security;

import java.util.List;

import javax.security.enterprise.CallerPrincipal;

import dto.User;
import lombok.Getter;

public class CustomPrincipal extends CallerPrincipal {

    @Getter
    private final String emailAddress;

    @Getter
    private final String password;

    @Getter
    private final List<String> roles;

    public CustomPrincipal(final User user, final List<String> roles) {
        super(user.getName().toString());

        this.emailAddress = user.getEmailaddress();
        this.password = user.getPassword();
        this.roles = roles;
    }

    public boolean hasRole(final String role) {
        return roles.contains(role);
    }
}
