package mode;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.transaction.TransactionSynchronizationRegistry;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@SessionScoped
@Slf4j
public class TestMode implements Serializable {

    @Getter
    private boolean test = false;

    @Resource
    private TransactionSynchronizationRegistry txRegistry;

    public void switchMode() {
        this.test = test ? false : true;
        log.debug("テストモードを切り替えたよ：{}", test);
    }

    public void setRollbackOnlyIfTestMode() {

        if (test) {
            txRegistry.setRollbackOnly();
            log.debug("ロールバック");
            return;
        }
        log.debug("コミット");
    }
}
