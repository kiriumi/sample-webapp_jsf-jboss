package domain;

import java.util.List;

import javax.enterprise.context.SessionScoped;

import lombok.Getter;

@SessionScoped
public class LoginUser {

    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final String password;

    @Getter
    private final List<String> roles;

    public LoginUser(String id, String name, String password, List<String> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

}
