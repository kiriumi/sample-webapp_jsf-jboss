package token;

import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lombok.Getter;
import lombok.Setter;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TokenCheck(child = true)
public class TokenCheckInterceptor {

    @Inject
    private FacesContext facesContext;

    @Inject
    private TokenBean tokenBean;

    @Inject
    private ChildrenTokenBean childrenTokenBean;

    @Getter
    @Setter
    private String token;

    @AroundConstruct
    public Object onConstract(final InvocationContext context) throws Exception {

        Object result = context.proceed();

        if (!facesContext.isPostback()) {
            // 初期表示の時
            if (getAnnotation(context).child()) {
                childrenTokenBean.verify(getAnnotation(context).check());
            } else {
                tokenBean.verify(getAnnotation(context).check());
            }
        }

        return result;
    }

    @AroundInvoke
    public Object onRedirect(final InvocationContext context) throws Exception {

        Object result = context.proceed();
        if (!isRedirect(result)) {
            return result;
        }

        if (getAnnotation(context).child()) {
            return childrenTokenBean.addTokenParams(result);
        }
        return tokenBean.addTokenParams(result);
    }

    private boolean isRedirect(Object result) {

        PhaseId currentPhaseId = facesContext.getCurrentPhaseId();
        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            return false;
        }

        if (result == null || !(result instanceof String)) {
            return false;
        }

        if (((String) result).endsWith("?faces-redirect=true")) {
            return true;
        }
        return false;
    }

    private TokenCheck getAnnotation(InvocationContext context) {
        return context.getTarget().getClass().getAnnotation(TokenCheck.class);
    }

}
