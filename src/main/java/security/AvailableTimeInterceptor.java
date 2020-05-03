package security;

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
@AvailableTime
@Slf4j
public class AvailableTimeInterceptor {

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

        if (available()) {
            return context.proceed();
        }

        if (facesContext.getMessageList().size() == 0) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "利用時間外だよ", null));
        }

        log.warn("利用時間外だよ");

        return null;
    }

    private boolean available() {

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (request.isUserInRole("admin")) {
            return true;
        }

        if (isAvailableTime()) {
            return true;
        }

        return false;
    }

    private boolean isAvailableTime() {

        LocalTime now = LocalTime.now();
        LocalTime nightTime = LocalTime.of(22, 0);
        LocalTime morningTime = LocalTime.of(7, 0);

        return now.isAfter(morningTime) && now.isBefore(nightTime);
    }

}
