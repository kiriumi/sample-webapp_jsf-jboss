package application;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import token2.TokenCheck;

@Named
@ViewScoped
@TokenCheck
public class Index3 extends BaseBackingBean2 {

    @Setter
    @Getter
    private String name;

    public String viewAction() {
        return null;
    }

    public String method() {
        System.out.println(name);
        return null;
    }

    public String go() {
        return redirect("index2");
    }
}
