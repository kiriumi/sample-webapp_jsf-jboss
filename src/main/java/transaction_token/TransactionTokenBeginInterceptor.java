package transaction_token;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

import domain.MessageService;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TransactionTokenBegin(namespace = "")
public class TransactionTokenBeginInterceptor {

    @Inject
    TransactionTokener tokener;

    @Inject
    MessageService message;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        TransactionTokenBegin annotation = context.getMethod().getAnnotation(TransactionTokenBegin.class);
        String namespace = annotation.namespace();

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespaceは必須にゃ");
        }

        tokener.begin(namespace);
        return context.proceed();
    }

}
