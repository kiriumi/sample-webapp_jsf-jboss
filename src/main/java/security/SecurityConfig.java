package security;

import javax.enterprise.context.ApplicationScoped;
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
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "/application/error.xhtml", useForwardToLogin = false))
@ApplicationScoped
public class SecurityConfig {

    // JavaEE Security API の参考
    //   https://github.com/rieckpil/blog-tutorials/tree/master/jsf-simple-login-with-java-ee-security-api
    //   https://github.com/openknowledge/java-ee-8-security-api-example/tree/master/src/main/java/de/openknowledge/samples/javaee8

    //    public String[] getDyna() {
    //        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    //    }
}
