package transaction_pages;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TransactionPages("")
public class TransactionPagesInterceptor {

    @Inject
    TransactionPagesBean transactionPage;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        // 参考：https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DoubleSubmitProtection.html#transactiontokencheck

        TransactionPages annotation = context.getMethod().getAnnotation(TransactionPages.class);
        transactionPage.begin(annotation);

        return context.proceed();
    }

}
