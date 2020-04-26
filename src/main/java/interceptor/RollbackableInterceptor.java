package interceptor;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import context.TestMode;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Rollbackable
public class RollbackableInterceptor {

    @Inject
    private TestMode testMode;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        Object result = context.proceed();

        testMode.setRollbackOnly();

        return result;
    }
}