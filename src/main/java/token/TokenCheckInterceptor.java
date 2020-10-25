package token;

import javax.annotation.Priority;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TokenCheck
public class TokenCheckInterceptor {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ExternalContext externalContext;

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
            // 初期表示の時にトークンチェックを行う
            if (isParent()) {
                tokenBean.verify(doCheck(context));
            } else {
                childrenTokenBean.verify(doCheck(context));
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

        if (isParent()) {
            return tokenBean.addTokenParams(result);
        }
        return childrenTokenBean.addTokenParams(result);
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

    private boolean doCheck(InvocationContext context) {
        return context.getTarget().getClass().getAnnotation(TokenCheck.class).check();
    }

    private boolean isParent() {
        String namespace = externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_TOKEN_NAMESPACE);
        String dialog = (String) externalContext.getRequestParameterMap().get(TokenHolder.REQ_PARAM_PF_DIALOG);
        return StringUtils.isBlank(namespace) && StringUtils.isBlank(dialog);
    }

}
