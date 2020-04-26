package application;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import interceptor.Redirect;
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
@Named
@RequestScoped
@Data
@EqualsAndHashCode(callSuper = false)
@Auth(emailAddress = "emailAddress", password = "password")
public class LoginBean extends AbstractManagedBean {

    @NotBlank(groups = AuthGroup.class)
    @Email
    private String emailAddress;

    @NotBlank(groups = AuthGroup.class)
    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    private String password;

    @Inject
    UserService userService;

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

    public void authenticate(final ActionEvent event) {

        if (userService.find(emailAddress, password)) {
            this.authed = true;
        }

        setError(event, "error.message.login");
    }

    public String login() {

        if (authed) {
            getFlash().put("loginSccessMessage", "ログインできたよ");
            return "loginSuccess.xhtml";
        }

        return null;
    }

    @Redirect
    public String goSignupPage() {
        //        return "signup?faces-redirect=true";
        return "signup";
    }

    @Redirect
    public String goLogoutPage() {
        return "logout";
    }

}
