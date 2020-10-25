package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import lombok.Getter;
import lombok.Setter;
import token.TokenCheck;
import token.TokenHolder;

@Model
@TokenCheck(check = false)
public class ParentWindow2Bean extends BaseBackingBean {

    @Getter
    @Setter
    private String dummy;

    @Inject
    ExternalContext externalContext;

    @Inject
    TokenHolder tokenHolder;

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

    public void showDialog() {
        PrimeFaces.current().dialog().openDynamic("dynamic-dialog.xhtml");
        //        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "メッセージ", "詳細"));
    }

}
