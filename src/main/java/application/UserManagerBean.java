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
    @Setter
    private List<User> searchedUsers;

    public String searchByJpql() {
        this.searchedUsers = userService.searchByJpql(emailAddress, lastName);
        return null;
    }

    public String searchBySqlWithJpa() {
        List results = userService.searchBySql(emailAddress, lastName);
        return null;
    }

    public String searchByCriteriaApi() {
        this.searchedUsers = userService.serchByCriteriaApi(emailAddress, lastName);
        return null;
    }

}
