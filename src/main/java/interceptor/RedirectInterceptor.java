package interceptor;

import javax.annotation.Priority;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
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

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PhaseId phaseId = facesContext.getCurrentPhaseId();

        //        facesContext.getExternalContext().

        UIViewRoot viewRoot = facesContext.getViewRoot();

        Object outcome = context.proceed();
        outcome = outcome.toString() + "?faces-redirect=true";

        logger.debug("リダイレクトのパラメータ付与：{}", outcome);

        return outcome;
    }
}
