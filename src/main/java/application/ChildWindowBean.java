package application;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import domain.ChildWindowData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import token.TokenCheck;

@Model
@TokenCheck(child = true)
@Slf4j
public class ChildWindowBean extends BaseBackingBean {

    @Inject
    @Getter
    @Setter
    private ChildWindowData data;

    public String childWindowAction() {
        log.debug("サブウィンドウのアクション");
        return null;
    }

    public String goChild2() {
        return "child-window2?faces-redirect=true";
    }

}
