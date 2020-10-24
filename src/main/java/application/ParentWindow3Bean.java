package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import token.TokenCheck;

@Model
@TokenCheck
public class ParentWindow3Bean extends BaseBackingBean {

    @Inject
    ExternalContext externalContext;

    @Getter
    @Setter
    private String dummy;

    public String goParent2() {
        return "parent-window2?faces-redirect=true";
    }
}
