package application;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class IndexChild2 extends BaseBackingBean2 {

    @Setter
    @Getter
    private String name;

    public String viewAction() {
        return null;
    }

    public String method() {
        return null;
    }

    public String go() {
        return redirect("index-child3");
    }

}
