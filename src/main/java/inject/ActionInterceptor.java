package inject;

import java.time.LocalTime;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Action
@Slf4j
public class ActionInterceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PhaseId currentPhaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();

        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            // アクション以外は、処理の前後でログを出力しない
            return context.proceed();
        }

        if (refOnly()) {

            if (facesContext.getMessageList().size() == 0) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "時間外だよ", null));
            }
            return null;
        }

        // そのままだとProxyである印が付くため除去
        String classNameWithoutProxy = context.getTarget().getClass().getName().replaceAll("\\$Proxy.*$", "");
        String methodName = context.getMethod().getName();

        log.debug("{}#{} 開始", classNameWithoutProxy, methodName);

        Object result = null;

        try {
            result = context.proceed();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        log.debug("{}#{} 終了", classNameWithoutProxy, methodName);

        return result;
    }

    private boolean refOnly() {

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (request.getUserPrincipal() == null) {
            return false;
        }

        if (request.isUserInRole("admin")) {
            return false;
        }

        if (worikingTime()) {
            return false;
        }

        return true;
    }

    private boolean worikingTime() {

        LocalTime now = LocalTime.now();
        LocalTime nightTime = LocalTime.of(22, 0);
        LocalTime morningTime = LocalTime.of(7, 0);

        return now.isAfter(morningTime) && now.isBefore(nightTime);
    }
}
