package domain;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class UserSearchCondition implements Serializable {

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String firstName;

    private boolean needInitSearch = false;

    public void clear() {
        this.emailAddress = null;
        this.lastName = null;
        this.firstName = null;
    }

    public boolean needInitSearch() {

        boolean ret = needInitSearch;
        this.needInitSearch = false;

        return ret;
    }

    public void setInitNeedSearch() {
        this.needInitSearch = true;
    }

}
