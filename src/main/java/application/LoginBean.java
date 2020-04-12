package application;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import domain.UserService;
import interceptor.Logging;
import lombok.Data;
import lombok.EqualsAndHashCode;
import validation.Auth;
import validation.LoginGroup;

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
@Logging
public class LoginBean extends AbstractManagedBean {

	@NotBlank(groups = LoginGroup.class)
	private String emailAddress;

	@NotBlank(groups = LoginGroup.class)
	private String password;

	@Inject
	private UserService userService;

	@Override
	protected void init() {
		setInfoMessage((String) getFlash().get("signupSccessMessage"));
	}

	@Auth
	public void isAuth(final String emailAddress, final String password) {
	};

	public String login() {

		//		if (auth) {
		//			getFlash().put("loginSccessMessage", "ログインできたよ");
		//			return "loginSuccess.xhtml";
		//		}

		ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");
		String message = resourceBundle.getString("error.message.auth");

		System.out.println(message);

		return null;
	}

	public String goSignupPage() {
		return "signup.xhtml";

	}

}
