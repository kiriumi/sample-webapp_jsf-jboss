package log;

import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@ActionLogging
@Slf4j
public class ActionLogInterceptor {

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PhaseId currentPhaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();

        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            // アクション以外は、処理の前後でログを出力しない
            return context.proceed();
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
}
