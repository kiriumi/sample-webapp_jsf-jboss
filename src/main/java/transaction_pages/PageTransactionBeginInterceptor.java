package transaction_pages;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@PageTransactionBegin("")
public class PageTransactionBeginInterceptor {

    @Inject
    PageTransaction pageTransaction;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        // 参考：https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DoubleSubmitProtection.html#transactiontokencheck

        PageTransactionBegin annotation = context.getMethod().getAnnotation(PageTransactionBegin.class);
        pageTransaction.begin(annotation);

        return context.proceed();
    }

}
