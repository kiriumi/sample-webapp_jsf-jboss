package security;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@AvailableTime
@Slf4j
public class AvailableTimeInterceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    AviableTimeValidator aviableValidator;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PhaseId currentPhaseId = facesContext.getCurrentPhaseId();
        if (!currentPhaseId.equals(PhaseId.INVOKE_APPLICATION)) {
            // アクション以外は何もしない
            return context.proceed();
        }

        if (isLoginLogout(context) || aviableValidator.available()) {
            return context.proceed();
        }

        if (facesContext.getMessageList().size() == 0) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "利用時間外だよ", null));
        }

        log.warn("利用時間外だよ");

        return null;
    }

    private boolean isLoginLogout(final InvocationContext context) {

        LoginLogout loginLogoutAnnotation = context.getMethod().getAnnotation(LoginLogout.class);
        return loginLogoutAnnotation != null;
    }

}
