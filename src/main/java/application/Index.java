package application;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class Index extends BaseBackingBean2 {

    @Setter
    @Getter
    private String name;

    public String viewAction() {

        name = "\"<script> ;";
        return null;
    }

    public String method() {

        System.out.println(name);

        HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        res.addCookie(new Cookie("hoge", "hogehoge"));

        return null;
    }

    public String go() {
        return redirect("index2");
    }

}
