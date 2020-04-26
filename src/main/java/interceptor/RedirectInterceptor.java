package interceptor;

import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Redirect
public class RedirectInterceptor {

    private final Logger LOGGER = LogManager.getLogger(LogInterceptor.class);

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        LOGGER.debug("リクエスト：{}", FacesContext.getCurrentInstance().getExternalContext().getRequest().toString());
        LOGGER.debug("レスポンス：{}", FacesContext.getCurrentInstance().getExternalContext().getResponse().toString());

        Object outcome = context.proceed();
        outcome = outcome.toString() + "?faces-redirect=true";

        LOGGER.debug("リダイレクトのパラメータ付与：{}", outcome);

        return outcome;
    }
}
