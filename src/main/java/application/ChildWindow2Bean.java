package application;

import javax.enterprise.inject.Model;

import token.TokenCheck;

@Model
@TokenCheck
public class ChildWindow2Bean extends BaseBackingBean {

    public String goChild() {
        return "child-window?faces-redirect=true";
     }

}
