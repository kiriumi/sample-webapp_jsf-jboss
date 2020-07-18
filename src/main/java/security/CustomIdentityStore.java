package security;

import java.security.Principal;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import domain.UserService;
import dto.User;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CustomIdentityStore implements IdentityStore {

    @Inject
    UserService userService;

    @Override
    public CredentialValidationResult validate(final Credential credential) {

        UsernamePasswordCredential userPassCrediental = (UsernamePasswordCredential) credential;

        String emailAddress = userPassCrediental.getCaller();
        String password = userPassCrediental.getPasswordAsString();

        log.debug("認証開始 {}/{}", emailAddress, password);

        User user = userService.find(emailAddress, password);

        if (userPassCrediental.compareTo(user.getEmailaddress(), user.getPassword())) {

            log.debug("認証成功");

            List<String> roles = userService.getRolesOnly(user);

            return new CredentialValidationResult(new CallerPrincipal(emailAddress), new HashSet<>(roles));

            //            return new CredentialValidationResult(new CustomPrincipal(user, roles), new HashSet<>(roles));
            //            return new CredentialValidationResult(user.getEmailaddress());
        }

        log.debug("認証失敗");

        return CredentialValidationResult.INVALID_RESULT;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        //        return EnumSet.of(ValidationType.VALIDATE);
        return EnumSet.of(ValidationType.PROVIDE_GROUPS);
    }

    @Override
    public Set<String> getCallerGroups(final CredentialValidationResult validationResult) {

        Principal principal = validationResult.getCallerPrincipal();
        List<String> roles = userService.getRoles(principal.getName());
        return new HashSet<>(roles);
    }

    @Override
    public int priority() {
        return 90;
    }

}
