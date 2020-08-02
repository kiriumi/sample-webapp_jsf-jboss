package log;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import application.BaseBackingBean;
import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION - 10)
@ActionLogging
@Slf4j
public class ActionLoggingInterceptor implements Serializable { // ViewScoped以上のBackingビーンで使う場合、Serializableが必要

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PhaseId currentPhaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            // アクション以外は何もしない
            return context.proceed();
        }

        // そのままだとProxyである印が付くため除去
        String classNameWithoutProxy = context.getTarget().getClass().getName().replaceAll("\\$Proxy.*$", "");
        String methodName = context.getMethod().getName();

        log.info("{}#{} 開始", classNameWithoutProxy, methodName);
        logBeanPropertiesIfRedirect(context, methodName);

        Object result = context.proceed();

        logBeanPropertiesIfRedirect(context, methodName);
        log.info("{}#{} 終了", classNameWithoutProxy, methodName);

        return result;
    }

    private void logBeanPropertiesIfRedirect(final InvocationContext context, final String methodName) {

        if (methodName.equals("redirect")) {
            try {
                BaseBackingBean backingBean = (BaseBackingBean) context.getTarget();
                log.info(backingBean.toString());

            } catch (Exception e) {
            }
        }
    }

}
