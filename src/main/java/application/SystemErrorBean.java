package application;

import javax.enterprise.inject.Model;

/**
 * システム障害画面
 * @author kengo
 *
 */
@Model
public class SystemErrorBean extends BaseBackingBean {

    public String goTopPage() {
        return redirect("top");
    }
}
