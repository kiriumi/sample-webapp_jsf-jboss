package interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Redirect
public class RedirectInterceptor {

    Logger logger = LogManager.getLogger();

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        Object outcome = context.proceed();
        outcome = outcome.toString() + "?faces-redirect=true";

        logger.debug("リダイレクトのパラメータ付与：{}", outcome);

        return outcome;
    }
}
