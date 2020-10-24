package application;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import domain.ChildWindowData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import token.TokenCheck;

@Model
@TokenCheck
@Slf4j
public class ParentWindowBean extends BaseBackingBean {

    @Inject
    @Getter
    @Setter
    private ChildWindowData data;

    @Inject
    ExternalContext externalContext;

    @PostConstruct
    public void init() {
        return;
    }

    public String goParent2() {
        return "parent-window2?faces-redirect=true";
    }
}
