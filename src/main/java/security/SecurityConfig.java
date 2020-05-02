package security;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

//@DatabaseIdentityStoreDefinition(
//        dataSourceLookup = "java:/PostgresDS",
//        callerQuery = "SELECT password FROM publc.user WHERE emailaddress = ?",
//        groupsQuery = "SELECT role, 'Roles' FROM publc.role WHERE emailaddress = ?",
//        hashAlgorithm = Pbkdf2PasswordHash.class,
//        priorityExpression = "#{100}",
//        hashAlgorithmParameters = {
//            "Pbkdf2PasswordHash.Iterations=3072",
//            "${applicationConfig.dyna}"
//        }
//)
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", useForwardToLogin = false))
@FacesConfig
@Named
@ApplicationScoped
public class SecurityConfig {

    //    JavaEE Security API の参考：https://github.com/rieckpil/blog-tutorials/tree/master/jsf-simple-login-with-java-ee-security-api

    //    public String[] getDyna() {
    //        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    //    }
}
