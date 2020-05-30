package security;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletResponse;

import domain.MessageService;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION - 5)
@ForbidenRoles
public class ForbidenRolesInteceptor {

    @Inject
    ExternalContext externalContext;

    @Inject
    private RoleAuthorizator roleAuther;

    @Inject
    MessageService messageService;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        ForbidenRoles annotation = context.getMethod().getAnnotation(ForbidenRoles.class);
        List<String> forbidenRoles = Arrays.asList(annotation.value());

        if (!roleAuther.authUserIn(forbidenRoles)) {

            messageService.setMessage(FacesMessage.SEVERITY_ERROR, "アクセス権限ないよ");

            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            return null;
        }

        return context.proceed();
    }

}
