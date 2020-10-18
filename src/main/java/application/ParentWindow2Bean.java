package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import token.TokenCheck;

@Model
@TokenCheck
public class ParentWindow2Bean extends BaseBackingBean {

    @Inject
    ExternalContext externalContext;

    public String goParent() {
        return "parent-window?faces-redirect=true";
    }

    public String goParent3() {
        return "parent-window3?faces-redirect=true";
    }

    public String action() {
        System.out.println("アクション");
        return null;
    }
}
