package mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Transactional
public @interface TestModeTransactional {

    // トランザクションの範囲の参考：https://qiita.com/sengoku/items/69ed0888964dd505d348
    TxType value() default TxType.REQUIRES_NEW;
}
