package application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import domain.UserService;
import interceptor.Logging;
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
@Auth(emailAddress = "emailAddress", password = "password")
@EqualsAndHashCode(callSuper = false)
@Logging
public class LoginBean extends AbstractManagedBean {

	@NotBlank(groups = AuthGroup.class)
	@Email
	private String emailAddress;

	@NotBlank(groups = AuthGroup.class)
	@Size(min = 4, max = 8)
	@Pattern(regexp = "[0-9a-zA-Z]*")
	private String password;

	@Inject
	private UserService userService;

	@Override
	protected void init() {
		setInfoMessage((String) getFlash().get("signupSccessMessage"));
	}

	//	/**
	//	 * JSFでなければ、メソッドバリデーションが行える
	//	 * @param emailAddress
	//	 * @param password
	//	 */
	//	@AuthSpring
	//	public void auth(final String emailAddress, final String password) {
	//	};

	public String login() {

		getFlash().put("loginSccessMessage", "ログインできたよ");
		return "loginSuccess.xhtml";
	}

	public String goSignupPage() {
		return "signup.xhtml";
	}

}
