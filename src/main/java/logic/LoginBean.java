package logic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import interceptor.Logging;

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
 *
 */
/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Named
@RequestScoped
public class LoginBean extends AbstractManagedBean {

    @NotNull(message = "IDは必須です")
    @Size(max = 5, message = "IDが長すぎます")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "IDに使用できない文字があります")
    private String id;

    private String password;

    private boolean isAuth;

    @Logging
    public void loginCheck() {

        if (id.equals("test") && password.equals("test")) {
            isAuth = true;
        }
    }

    public String login() {

        if (isAuth) {
            getFlash().put("loginSccessMessage", "ログインできましたの");
            return "loginSuccess.xhtml";
        }

        return null;
    }

    @Override
    public void init() {
        setId("test");
        setPassword("test");
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
