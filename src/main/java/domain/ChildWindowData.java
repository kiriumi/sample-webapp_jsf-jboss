package domain;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class ChildWindowData implements Serializable {

    @Getter
    @Setter
    private String parentToChild;

    @Getter
    @Setter
    private String childToParent;

    @Getter
    @Setter
    private String childToGrandChild;

    @Getter
    @Setter
    private String grandChildToChild;
}
