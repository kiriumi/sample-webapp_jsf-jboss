package transaction_pages;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import context.WebApplicationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TransactionPagesEnd
public class TransactionPagesEndInterceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    WebApplicationContext appContext;

    @Inject
    TransactionPagesBean transactionPage;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        if (!transactionPage.valid()) {

            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "エラー", "ちゃんとしたルートから登録してよー"));
            facesContext.getExternalContext().getFlash().setKeepMessages(true);

            return appContext.redirectSystemErrorPage();
        }

        return context.proceed();
    }

}
