package application;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Model
public class LogoutBean {

    @Inject
    ExternalContext externalContext;

    public void viewAction() {
        externalContext.invalidateSession();
    }

}
