package application;

import static javax.faces.application.FacesMessage.*;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import validation.Auth;
import validation.AuthGroup;

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
 * ログインページのモデルクラス
 *
 * @author Kengo
 *
 */
@Model // @Named＋@RequestScoped
@Data
@EqualsAndHashCode(callSuper = false)
@Auth(emailAddress = "emailAddress", password = "password")
//@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
@FacesConfig
public class LoginBean extends BaseBackingBean {

    @NotBlank(groups = AuthGroup.class)
    @Email
    private String emailAddress;

    @NotBlank(groups = AuthGroup.class)
    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    private String password;

    @Inject
    UserService userService;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;

    private boolean authed = false;

    @Override
    protected void preConstruct() {
        setInfoMessage((String) getFlash().get("signupSccessMessage"));
    }

    //	/**
    //	 * Springでなければ、メソッドバリデーションが行える
    //	 * @param emailAddress
    //	 * @param password
    //	 */
    //	@AuthSpring
    //	public void auth(final String emailAddress, final String password) {
    //	};

    private AuthenticationStatus continueAuthentication() {

        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(emailAddress, password)));
    }

    public void authenticate(final ActionEvent event) throws IOException {

        switch (continueAuthentication()) {
        case SEND_CONTINUE:
            facesContext.responseComplete();
            break;
        case SEND_FAILURE:
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
            break;
        case SUCCESS:
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));

            this.authed = true;

            externalContext.redirect(externalContext.getRequestContextPath() + "loginSuccess.xhtml");
            break;
        case NOT_DONE:
        }

        //        Credential credential = new UsernamePasswordCredential(emailAddress, new Password(password));
        //
        //        AuthenticationStatus status = securityContext.authenticate(getRequest(), getResoponse(),
        //                withParams().credential(credential));
        //
        //        if (status.equals(SEND_CONTINUE)) {
        //            facesContext.responseComplete();
        //        } else if (status.equals(SEND_FAILURE)) {
        //            addError("Authentication failed");
        //        }

        //        HttpServletRequest request = getRequest();
        //        try {
        //            getLogger().info("anything", "認証開始");
        //            request.login(emailAddress, password);
        //            User user = userService.find(emailAddress, password);
        //
        //            if (user != null) {
        //
        //                this.authed = true;
        //                getLogger().info("anything", "認証成功");
        //                return;
        //            }
        //
        //        } catch (ServletException e) {
        //            e.printStackTrace();
        //        }
        //
        //        getLogger().info("anything", "認証失敗");
        //        setError(event, "error.message.login");
    }

    public String login() {

        if (authed) {
            getFlash().put("loginSccessMessage", "ログインできたよ");
            return redirect("loginSuccess");
        }

        return null;
    }

    public String goSignupPage() throws Exception {
        return redirect("signup");
    }

    public String goLogoutPage() {
        return redirect("logout");
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) facesContext
                .getExternalContext()
                .getRequest();
    }

    private HttpServletResponse getResoponse() {
        return (HttpServletResponse) facesContext
                .getExternalContext()
                .getResponse();
    }

    private void addError(final String message) {
        facesContext
                .addMessage(
                        null,
                        new FacesMessage(SEVERITY_ERROR, message, null));
    }
}
