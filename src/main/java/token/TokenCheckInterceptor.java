package token;

import javax.annotation.Priority;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lombok.Getter;
import lombok.Setter;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TokenCheck
public class TokenCheckInterceptor {

    @Inject
    TokenBean tokenBean;

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Getter
    @Setter
    private String tokenInPage;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PhaseId currentPhaseId = facesContext.getCurrentPhaseId();

        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            // アクション以外は何もしない
            // ※immediateのアクションは、Apply Request Valueフェーズのため注意
            return context.proceed();
        }

        Object result = context.proceed();
        if (result == null) {
            // 自画面遷移の場合は、何もしない
            return result;
        }

        // 他画面遷移の場合は、トークンを付与
        return result + "token=" + tokenBean.getToken();
    }

}
