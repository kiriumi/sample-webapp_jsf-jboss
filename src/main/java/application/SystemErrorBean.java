package application;

import javax.enterprise.inject.Model;

@Model
public class SystemErrorBean extends BaseBackingBean {

    public String goTopPage() {
        return redirect("top");
    }
}
