package application;

import javax.enterprise.inject.Model;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import context.WebApplicationContext;
import domain.UserService;
import dto.User;
import lombok.Getter;
import lombok.Setter;
import security.custom.CustomPrincipal;

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
@FacesConfig
public class LoginCustomBean extends BaseBackingBean {

    @Inject
    private ExternalContext externalContext;

    @Inject
    private WebApplicationContext appContext;

    @Inject
    private CustomPrincipal principal;

    @Inject
    private UserService userService;

    @Email
    @Getter
    @Setter
    private String emailAddress;

    @Size(min = 4, max = 8)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    @Getter
    @Setter
    private String password;

    public String login() {

        User user = userService.find(emailAddress, password);

        if (user == null) {
            messageService().addMessage(FacesMessage.SEVERITY_ERROR, "Eメールアドレスとパスワードが一致しないよ");
            return null;
        }

        // セッション・ハイジャック対策のため、セッションを作り直す
        externalContext.invalidateSession();
        externalContext.getSessionId(true);

        principal.setLogined(true);

        return redirect("top");
    }

}
