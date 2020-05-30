package application;

import javax.enterprise.inject.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Model
@Data
@EqualsAndHashCode(callSuper = false)
public class PrimeFacesSamplesBean extends BaseBackingBean {

    public String actionAfterConfirm() {
        return null;
    }
}
