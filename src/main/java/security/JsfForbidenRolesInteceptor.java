package security;

import java.util.Arrays;
import java.util.List;

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
@JsfForbidenRoles
@Slf4j
public class JsfForbidenRolesInteceptor {

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

        JsfPermittedRoles annotation = context.getMethod().getAnnotation(JsfPermittedRoles.class);
        List<String> forbidenRoles = Arrays.asList(annotation.value());

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.isUserInRole("admin");

        boolean forbiden = false;

        for (String forbidenRole : forbidenRoles) {

            if (request.isUserInRole(forbidenRole)) {
                forbiden = true;
                break;
            }
        }

        if (forbiden) {

            if (facesContext.getMessageList().size() == 0) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "利用権限ないよ", null));
                return null;
            }

            log.warn("利用権限ないよ");

            return null;
        }

        return context.proceed();
    }

}
