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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@PermittedRoles
@Slf4j
public class PermittedRolesInterceptor {

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        PermittedRoles annotation = context.getMethod().getAnnotation(PermittedRoles.class);
        List<String> permittedRoles = Arrays.asList(annotation.value());

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.isUserInRole("admin");

        boolean permitted = false;

        for (String permittedRole : permittedRoles) {

            if (request.isUserInRole(permittedRole)) {
                permitted = true;
                break;
            }
        }

        if (permitted) {
            return context.proceed();
        }

        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        return null;
    }

}
