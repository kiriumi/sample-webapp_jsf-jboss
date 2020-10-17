package token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@Inherited // InvoceContexでアノテーションを取得するには、Inheritedが必要
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface TokenCheck {

    public enum Type {
        BEGIN, CONTINUE;
    }

    @Nonbinding // このアノテーションを付けることで、パラメータを付けたアノテーションでインターセプタ―が可能になる
    public Type value() default Type.CONTINUE;
}
