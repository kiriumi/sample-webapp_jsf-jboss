package mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientException;

import javax.interceptor.InterceptorBinding;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = SQLException.class, dontRollbackOn = {
        SQLTransientException.class, SQLTransactionRollbackException.class })
public @interface TestModeTransactional {
}