package application;

import javax.enterprise.inject.Model;

import lombok.Getter;
import lombok.Setter;
import token.TokenCheck;

@Model
@TokenCheck
public class ChildWindow2Bean extends BaseBackingBean {

    @Getter
    @Setter
    private String dummy;

    public String goChild() {
        return "child-window?faces-redirect=true";
     }

}
