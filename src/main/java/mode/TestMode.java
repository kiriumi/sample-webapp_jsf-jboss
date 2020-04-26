package mode;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.transaction.TransactionSynchronizationRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import log.JpaEclipselinkLogger;
import lombok.Getter;

@SessionScoped
public class TestMode implements Serializable {

    private final Logger logger = LogManager.getLogger(JpaEclipselinkLogger.class);

    @Getter
    private boolean test = false;

    @Resource
    private TransactionSynchronizationRegistry txRegistry;

    public void switchMode() {
        this.test = test ? false : true;
    }

    public void setRollbackOnlyIfTesMode() {

        if (test) {
            txRegistry.setRollbackOnly();
            logger.debug("ロールバック");
            return;
        }

        logger.debug("コミット");
    }
}
