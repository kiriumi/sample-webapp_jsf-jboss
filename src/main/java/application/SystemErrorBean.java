package application;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import context.WebApplicationContext;

/**
 * システム障害画面
 * @author kengo
 *
 */
@Model
public class SystemErrorBean extends BaseBackingBean {

    @Inject
    private WebApplicationContext appContext;

    public String backPage() {
        return appContext.redirectNonSecuredPage("login");
    }

}
