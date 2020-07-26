package application;

import javax.enterprise.inject.Model;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import context.WebApplicationContext;
import domain.TwoFactorAuthenticator;
import domain.UserService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * クラスに付けるアノテーション（２種類）
 *
 * ■管理Bean
 *
 * @ManagedBean：JSF管理Bean
 * 例１：@ManagedBean ※nameを省略したら、デフォルトで先頭小文字のクラス名が名前になる
 * 例２：ManagedBean(name = "loginBean", eager = true)
 * ※eager：true…初回リクエストが来る前からインスタンス化する
 *
 * @Named：CDI管理Bean（JavaEE7はこちらを推奨）
 * ・name：
 * 例１：@Named ※nameを省略したら、デフォルトで先頭小文字のクラス名が名前になる
 * 例２：@Named("loginBean")
 *
 *
 * ■CDIの管理スコープ
 *
 * ※@ManagedBeanと@Namedで、名前は同じだが種類が異なるので注意
 * 例：RequestScoped
 * ・@ManagedBean → javax.faces.bean.RequestScoped
 * ・@Named → javax.enterprise.context.RequestScoped
 *
 * ・@RequestScoped：１リクエスト内
 * ・@ViewScoped：同一画面内
 * ・@SessionScoped：同一セッション内
 * ・@ApplicationScoped：アプリケーション内
 * ・@FlowScoped：開発者が指定した特定の画面遷移フロー内
 * ・@Dependent：注入先のスコープになる（デフォルトはこれ）
 *
 */
/**
 * ログイン画面クラス
 *
 * @author Kengo
 *
 */
@Model
@EqualsAndHashCode(callSuper = false)
@FacesConfig
//@Auth(emailAddress = "emailAddress", password = "password")
public class LoginBean extends BaseBackingBean {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private WebApplicationContext appContext;

    @Email
    @Getter
    @Setter
    private String emailAddress;

    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    @Getter
    @Setter
    private String password;

    @Inject
    UserService userService;

    @Inject
    TwoFactorAuthenticator authenticator;

    //	/**
    //	 * Springでなければ、メソッドバリデーションが行える
    //	 * @param emailAddress
    //	 * @param password
    //	 */
    //	@AuthSpring
    //	public void auth(final String emailAddress, final String password) {
    //	};

    public String viewAction() {

        if (authenticator.logined()) {
            return appContext.redirect("top");
        }

        if (authenticator.isFirstAuthed()) {
            return appContext.redirectNonSecuredPage("two-factor-auth");
        }

        return null;
    }

    public void authenticate(final ActionEvent event) throws Exception {

        //        AuthenticationStatus authStatus = getAuthStatus();
        //        logger.info("anything", "認証ステータス：" + authStatus);

        //        if (authStatus.equals(AuthenticationStatus.SUCCESS)) {
        //        if (true) {
        //            return;
        //        }

        //        messageService.setAppMessageById(FacesMessage.SEVERITY_ERROR, "error.message.auth");
    }

    private AuthenticationStatus getAuthStatus() {

        return securityContext.authenticate(
                (HttpServletRequest) externalContext().getRequest(),
                (HttpServletResponse) externalContext().getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(emailAddress, password)));
    }

    public String login() {

        if (authenticator.firstAuth(emailAddress, password)) {
            return appContext.redirectNonSecuredPage("two-factor-auth");
        }

        messageService().setAppMessageById(FacesMessage.SEVERITY_ERROR, "error.message.auth");

        return null;
    }

    public String goSignup() {
        return appContext.redirectNonSecuredPage("signup");
    }

}
