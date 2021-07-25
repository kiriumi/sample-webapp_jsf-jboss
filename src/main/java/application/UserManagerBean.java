package application;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import domain.UserSearchCondition;
import domain.UserService;
import dto.User;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped // DataTableは、同一画面内で情報共有するため、ViewScoped（しないとプロパティが取れない・アクションが起動しない）
public class UserManagerBean extends BaseBackingBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    @Getter
    @Setter
    private UserSearchCondition searchCond;

    @Getter
    private List<User> searchedUsers;

    @Getter
    private List<User> searchedUsersWithRoles;

    @Getter
    private LazyDataModel<User> searchedLazyUsers;

    @Setter
    private User selectedUser;

    @Getter
    @Setter
    private List<User> selectedUsers;

    @Getter
    @Setter
    private boolean searched = false;

    private boolean goDetail = false;

    @PostConstruct
    public void init() {

        if ((boolean) flash().getOrDefault("successDeleteUser", false)) {
            messageService().addMessage(FacesMessage.SEVERITY_INFO, "削除したよ");
        }

        if (searchCond.needInitSearch()) {
            searchByJpql();
        }
    }

    public String searchByJpql() {

        this.searchedUsers = userService.searchByJpql(
                searchCond.getEmailAddress(), searchCond.getLastName(), searchCond.getFirstName());

        this.searched = true;

        return null;
    }

    public String searchBySql() {

        this.searchedUsers = userService.searchBySql(
                searchCond.getEmailAddress(), searchCond.getLastName(), searchCond.getFirstName());

        this.searched = true;

        return null;
    }

    public String searchByCriteriaApi() {

        this.searchedUsers = userService.serchByCriteriaApi(
                searchCond.getEmailAddress(), searchCond.getLastName(), searchCond.getFirstName());

        this.searched = true;

        return null;
    }

    public String searchWithRolesByJpql() {

        this.searchedUsersWithRoles = userService.searchWithRolesByJpql(
                searchCond.getEmailAddress(), searchCond.getLastName());

        return null;
    }

    @Transactional
    public String deleteSelectedUsers() {

        if (selectedUsers.isEmpty()) {
            messageService().addMessage(FacesMessage.SEVERITY_WARN, "ユーザを選択してね");
            return null;
        }

        userService.deleteUsers(selectedUsers);
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "削除したよ");

        this.searchedUsers = userService.searchByJpql(
                searchCond.getEmailAddress(), searchCond.getLastName(), searchCond.getFirstName());

        return null;
    }

    public String goUserDetailPage() {

        this.goDetail = true;
        flash().put("selectedUser", selectedUser);
        return redirect("user-detail");
    }

    @PreDestroy
    public void fin() {

        if (!goDetail) {
            searchCond.clear();
        }
    }

    public void searcLazyUsers() {
        this.searchedLazyUsers = new LazyDataModel<User>() {
            @Override
            public List<User> load(
                    final int first, final int pageSize, final String sortField, final SortOrder sortOrder,
                    final Map<String, FilterMeta> filterBy) {
                List<User> users = userService.searchWithLazy(first, pageSize, sortField, sortOrder, filterBy);
                return users;
            }
        };
        searchedLazyUsers.setRowCount(userService.countAllUsers());
    }

}
