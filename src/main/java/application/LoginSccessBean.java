package application;

import javax.enterprise.inject.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Model // @Named＋@RequestScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginSccessBean extends BaseBackingBean {

    private String message;

    @Override
    public void preConstruct() {
        setInfoMessage((String) getFlash().get("loginSccessMessage"));
    }

}
