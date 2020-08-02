package application;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
    @Setter
    private String firstName;

    @Getter
    private List<User> searchedUsers;

    @Getter
    private List<User> searchedUsersWithRoles;

    @Setter
    private User selectedUser;

    @Getter
    @Setter
    private List<User> selectedUsers;

    @Getter
    @Setter
    private boolean searched = false;

    public String searchByJpql() {
        this.searchedUsers = userService.searchByJpql(emailAddress, lastName, firstName);
        this.searched = true;
        return null;
    }

    public String searchBySql() {
        this.searchedUsers = userService.searchBySql(emailAddress, lastName, firstName);
        this.searched = true;
        return null;
    }

    public String searchByCriteriaApi() {
        this.searchedUsers = userService.serchByCriteriaApi(emailAddress, lastName, firstName);
        this.searched = true;
        return null;
    }

    public String searchWithRolesByJpql() {
        this.searchedUsersWithRoles = userService.searchWithRolesByJpql(emailAddress, lastName);
        return null;
    }

    public String goSelectedUserDetailPage() {
        flash().put("selectedUser", selectedUser);
        return redirect("user-detail");
    }

    @Transactional
    public String deleteSelectedUsers() {

        if (selectedUsers.isEmpty()) {
            messageService().addMessage(FacesMessage.SEVERITY_WARN, "ユーザを選択してね");
            return null;
        }

        userService.deleteUsers(selectedUsers);
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "削除したよ");

        this.searchedUsers = userService.searchByJpql(emailAddress, lastName, firstName);

        return null;
    }

}
