package logic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Named
@RequestScoped
public class LoginSccessBean extends AbstractManagedBean {

    private String message;

    @Override
    public void init() {

        String message = (String) getFlash().get("loginSccessMessage");
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
