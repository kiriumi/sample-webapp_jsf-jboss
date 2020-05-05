package security;

import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
        if (user != null) {

            log.debug("認証成功");

            List<String> roles = userService.getRolesOnly(user);
            return new CredentialValidationResult(new CustomPrincipal(user, roles), new HashSet<>(roles));
        }

        log.debug("認証失敗");

        return CredentialValidationResult.INVALID_RESULT;
    }

    @Override
    public int priority() {
        return IdentityStore.super.priority();
    }

}
