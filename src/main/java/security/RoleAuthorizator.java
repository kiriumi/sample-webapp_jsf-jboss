package security;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

@RequestScoped
public class RoleAuthorizator {

    @Inject
    ExternalContext externalContext;

    public boolean authUserIn(final List<String> roles) {

        for (String role : roles) {
            if (externalContext.isUserInRole(role)) {
                return true;
            }
        }
        return false;
    }

}
