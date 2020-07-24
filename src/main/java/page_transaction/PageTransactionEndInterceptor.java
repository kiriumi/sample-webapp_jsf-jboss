package page_transaction;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import context.RedirectContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@PageTransactionEnd
public class PageTransactionEndInterceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    RedirectContext appContext;

    @Inject
    PageTransaction pageTransaction;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        if (!pageTransaction.valid()) {

            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "エラー", "ちゃんとしたルートから登録してよー"));
            facesContext.getExternalContext().getFlash().setKeepMessages(true);

            return appContext.redirectSystemErrorPage();
        }

        return context.proceed();
    }

}
