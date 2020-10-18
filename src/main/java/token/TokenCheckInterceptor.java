package token;

import javax.annotation.Priority;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@TokenCheck
public class TokenCheckInterceptor {

    @Inject
    private TokenHolder tokenHolder;

    @Inject
    TokenBean tokenBean;

    @Inject
    ChildrenTokenBean childrenTokenBean;

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

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

        String isChild = (String) externalContext.getRequestParameterMap().get(TokenHolder.ITEM_ID_TOKEN_NAMESPACE);

        // 他画面遷移の場合は、トークンを付与
        if (StringUtils.isBlank(isChild)) {
            tokenHolder.clearChildrenToken();
            return result + String.join("&", TokenHolder.REQ_PARAM_OKEN + "=" + tokenBean.getToken());
        }

        return result + String.join("&",
                TokenHolder.REQ_PARAM_TOKEN_NAMESPACE + "=" + childrenTokenBean.getNamespace(),
                TokenHolder.REQ_PARAM_OKEN + "=" + childrenTokenBean.getToken());
    }

}
