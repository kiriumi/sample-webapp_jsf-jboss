package interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Logging
//public class LogInterceptor {

public class LogInterceptor {

    @AroundInvoke
    public Object around(final InvocationContext ic) throws Exception {

        System.out.println("インターセプタ―開始");

        Object result = ic.proceed();

        System.out.println("インターセプタ―終了");

        return result;

    }
}
