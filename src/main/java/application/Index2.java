package application;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class Index2 extends BaseBackingBean2 {

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
        return redirect("index3");
    }

    public String openChild() {
        return null;
    }
}
