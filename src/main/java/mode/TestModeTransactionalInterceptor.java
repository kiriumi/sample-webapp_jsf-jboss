package mode;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TestModeTransactional
public class TestModeTransactionalInterceptor {

    @Inject
    private TestMode testMode;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        // トランザクションの範囲の参考：https://qiita.com/sengoku/items/69ed0888964dd505d348
        // SQL例外の種類の参考：http://itdoc.hitachi.co.jp/manuals/3020/3020645640/W4560552.HTM

        Object result = context.proceed();

        testMode.setRollbackOnlyIfTestMode();

        return result;
    }
}
