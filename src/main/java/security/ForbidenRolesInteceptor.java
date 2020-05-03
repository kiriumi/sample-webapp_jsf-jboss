package security;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@ForbidenRoles
@Slf4j
public class ForbidenRolesInteceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    ServletContext servletContext;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PermittedRoles annotation = context.getMethod().getAnnotation(PermittedRoles.class);
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

            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            log.warn("利用権限ないよ");

            return null;
        }

        return context.proceed();
    }

}
