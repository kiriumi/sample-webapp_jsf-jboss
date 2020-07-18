package security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

//@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "", useForwardToLogin = false)) // 認証失敗時に自画面遷移させるため、空文字を指定

@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:/PostgresDS", callerQuery = "SELECT password FROM public.user WHERE emailaddress = ?", groupsQuery = "SELECT role FROM public.role WHERE role.emailaddress = ?", priority = 30)
@ApplicationScoped
@Named
public class CustomFormAuthenticationMechanism {

    // JavaEE Security API の参考
    //   https://github.com/rieckpil/blog-tutorials/tree/master/jsf-simple-login-with-java-ee-security-api
    //   https://github.com/openknowledge/java-ee-8-security-api-example/tree/master/src/main/java/de/openknowledge/samples/javaee8
}
