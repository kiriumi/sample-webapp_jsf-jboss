package interceptor;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import mode.TestMode;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TestModeTransactional
public class TestModeTransactionalInterceptor {

    @Inject
    private TestMode testMode;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        Object result = context.proceed();

        testMode.setRollbackOnlyIfTesMode();

        return result;
    }
}
