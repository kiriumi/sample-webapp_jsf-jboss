package application;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import domain.UserService;
import dto.User;
import inject.ViewModel;
import lombok.Getter;
import lombok.Setter;

@ViewModel // DataTableは、同一画面内で情報共有するため、ViewScoped（しないとプロパティが取れない・アクションが起動しない）
public class UserManagerBean extends BaseBackingBean implements Serializable {

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

    @Setter
    private User selectedUser;

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

    public String goUserDetailPage() {
        return redirect("user-detail");
    }

    public String selected() {
        return null;
    }

}
