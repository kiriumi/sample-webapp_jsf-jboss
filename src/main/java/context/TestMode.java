package context;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.transaction.TransactionSynchronizationRegistry;

import lombok.Getter;

@SessionScoped
public class TestMode implements Serializable {

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
        }
    }
}
