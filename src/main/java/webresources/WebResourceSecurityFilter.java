package webresources;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import domain.UserService;
import dto.User;
import lombok.extern.slf4j.Slf4j;
import security.CustomPrincipal;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
@Slf4j
public class WebResourceSecurityFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {

        User user = authenticate(requestContext);
        List<String> roles = userService.getRolesOnly(user);

        setSecurityContext(requestContext, user, roles);
    }

    private User authenticate(final ContainerRequestContext requestContext) {

        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
            return null;
        }

        String credientals = authorization.split(" ")[1];

        byte[] decodedAuthByte = Base64.getDecoder().decode(credientals);
        String decodedAuth = new String(decodedAuthByte);

        String[] splitedAuth = decodedAuth.split(":");
        String emailAddress = splitedAuth[0];
        String password = splitedAuth[1];

        log.debug("RESTのBasic認証開始：{}/{}", emailAddress, password);

        return userService.find(emailAddress, password);
    }

    private void setSecurityContext(final ContainerRequestContext requestContext, final User user,
            final List<String> roles) {
        SecurityContext securityContext = new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return new CustomPrincipal(user, roles);
            }

            @Override
            public boolean isUserInRole(final String role) {
                return roles.contains(role);
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return "BASIC";
            }
        };

        requestContext.setSecurityContext(securityContext);
    }

}
