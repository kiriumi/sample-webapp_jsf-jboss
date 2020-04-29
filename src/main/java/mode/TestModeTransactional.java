package mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;

import javax.interceptor.InterceptorBinding;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = SQLException.class)
public @interface TestModeTransactional {

    // トランザクションの範囲の参考：https://qiita.com/sengoku/items/69ed0888964dd505d348
}