package application;

import log.ActionLogging;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActionLogging
public abstract class BaseBackingBean2 extends BaseBackingBean {

    public void additionalFunc() {
        log.debug("追加機能");
    }

}
