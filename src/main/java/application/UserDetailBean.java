package application;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.transaction.Transactional;

import domain.UserSearchCondition;
import domain.UserService;
import dto.User;
import inject.ViewModel;
import lombok.Getter;
import lombok.Setter;

@ViewModel
public class UserDetailBean extends BaseBackingBean implements Serializable {

    @Inject
    private UserService userService;

    @Getter
    @Setter
    private User user;

    @Inject
    UserSearchCondition searchCond;

    private boolean isBack = false;

    @PostConstruct
    public void init() {
        this.user = (User) flash().get("selectedUser");
    }

    @Transactional
    public String modifyUser() {

        this.user = userService.modifyUser(user);
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "更新したよ");

        return null;
    }

    @Transactional
    public String deleteUser() {

        userService.deleteUser(user);
        flash().put("successDeleteUser", true);

        return redirect("user-manager");
    }

    public String back() {
        this.isBack = true;
        return redirect("user-manager");
    }

    @PreDestroy
    public void fin() {

        if (!isBack) {
            searchCond.clear();
        } else {
            searchCond.setInitNeedSearch();
        }

    }

}
