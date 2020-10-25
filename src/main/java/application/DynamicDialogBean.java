package application;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import org.primefaces.PrimeFaces;

import lombok.Getter;
import lombok.Setter;
import token.TokenCheck;

@Model
@TokenCheck
public class DynamicDialogBean implements Serializable {

    @Getter
    @Setter
    private String item;

    @PostConstruct
    public void init() {
        this.item = "値";
    }

    public String closeDialog() {
        PrimeFaces.current().dialog().closeDynamic(item);
        return null;
    }

}
