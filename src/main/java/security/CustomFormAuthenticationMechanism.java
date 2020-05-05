package security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "", useForwardToLogin = false)) // 認証失敗時に自画面遷移させるため、空文字を指定
@ApplicationScoped
public class CustomFormAuthenticationMechanism {

    // JavaEE Security API の参考
    //   https://github.com/rieckpil/blog-tutorials/tree/master/jsf-simple-login-with-java-ee-security-api
    //   https://github.com/openknowledge/java-ee-8-security-api-example/tree/master/src/main/java/de/openknowledge/samples/javaee8

}
