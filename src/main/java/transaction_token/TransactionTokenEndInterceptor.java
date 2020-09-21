package transaction_token;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

import domain.MessageService;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TransactionTokenEnd(namespace = "")
public class TransactionTokenEndInterceptor {

    @Inject
    TransactionTokener tokener;

    @Inject
    MessageService message;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        TransactionTokenEnd annotation = context.getMethod().getAnnotation(TransactionTokenEnd.class);
        String namespace = annotation.namespace();

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespaceは必須にゃ");
        }

        if (tokener.valid(namespace)) {
            tokener.end();
            return context.proceed();
        }

        tokener.end();
        message.addMessage(FacesMessage.SEVERITY_ERROR, annotation.errorMessage());

        return null;
    }

}
