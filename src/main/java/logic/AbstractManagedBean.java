package logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

public abstract class AbstractManagedBean {

    /**
     * 管理Beanを初期化する際、実行する処理処理
     */
    @PostConstruct
    public void doInit() {
        init();
    }

    /**
     * 管理Beanを破棄する際、実行する処理
     */
    @PreDestroy
    public void doFin() {
        fin();
    }

    protected void init() {

    }

    protected void fin() {

    }

    /**
     * Flashを取得する
     * ※ 画面遷移の１度きりだけ有効になるマップ
     *
     * @return
     */
    protected Flash getFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    protected HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true);
    }

}
