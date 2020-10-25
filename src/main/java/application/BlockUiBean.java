package application;

import javax.enterprise.inject.Model;

import lombok.extern.slf4j.Slf4j;

@Model
@Slf4j
public class BlockUiBean extends BaseBackingBean {

    public String action() {
        log.debug("通常");
        return null;
    }

    public String actionAjax() {
        log.debug("Ajax");
        return null;
    }

}
