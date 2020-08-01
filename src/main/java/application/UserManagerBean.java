package application;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import domain.UserService;
import dto.User;
import lombok.Getter;
import lombok.Setter;

@Model
public class UserManagerBean {

    @Inject
    private UserService userService;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String lastName;

    @Getter
    private List<User> searchedUsers;

    @Getter
    private List<User> searchedUsersWithRoles;

    @Getter
    @Setter
    private List<User> selectedUsers;

    public String searchByJpql() {
        this.searchedUsers = userService.searchByJpql(emailAddress, lastName);
        return null;
    }

    public String searchBySql() {
        this.searchedUsers = userService.searchBySql(emailAddress, lastName);
        return null;
    }

    public String searchByCriteriaApi() {
        this.searchedUsers = userService.serchByCriteriaApi(emailAddress, lastName);
        return null;
    }

    public String searchWithRolesByJpql() {
        this.searchedUsersWithRoles = userService.searchWithRolesByJpql(emailAddress, lastName);
        return null;
    }

    public String selected() {
        return null;
    }
}
