package mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientException;

import javax.interceptor.InterceptorBinding;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = SQLException.class, dontRollbackOn = {
        SQLTransientException.class, SQLTransactionRollbackException.class })
public @interface TestModeTransactional {

    // トランザクションの範囲の参考：https://qiita.com/sengoku/items/69ed0888964dd505d348
    // SQL例外の種類の参考：http://itdoc.hitachi.co.jp/manuals/3020/3020645640/W4560552.HTM
}