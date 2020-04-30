package application;

import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import dto.User;
import log.Logging;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;

@Slf4j
@Logging

@Model

public abstract class AbstractManagedBean {

    @Inject
    private TestMode testMode;

    @Inject
    private TraceMode traceMode;

    @Getter
    private String infoMessage;

    @Getter
    @Setter
    private User user;

    /**
     * 管理Beanを初期化する際、実行する処理処理
     */
    @PostConstruct
    public void doPreConstruct() {
        log.debug("preConstruct");
        preConstruct();
    }

    public void preRender() {
        log.debug("preRender");
    }

    public void viewAction() {
        log.debug("viewAction");
    }

    /**
     * 管理Beanを破棄する際、実行する処理
     */
    @PreDestroy
    public void doFin() {
        fin();
    }

    protected void preConstruct() {

    }

    protected void fin() {

    }

    public void switchTestMode() {
        testMode.switchMode();
    }

    public void switchTraceMode() {
        traceMode.switchMode();
    }

    /**
     * Flashを取得する
     * ※ １度の画面遷移の間だけ有効な、情報受渡しマップ
     *
     * @return
     */
    protected Flash getFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    /**
     * セッションを取得する
     * @return セッション
     */
    protected HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true);
    }

    protected void setInfoMessage(final String infoMessage) {
        this.infoMessage = Optional.ofNullable(infoMessage).orElse("");
    }

    protected void setError(final ActionEvent event, final String messageId) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");
        FacesContext context = event.getFacesContext();

        UIComponent component = event.getComponent();

        String errorEessage = resourceBundle.getString(messageId);

        FacesMessage message = new FacesMessage(errorEessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(component.getClientId(), message);
        context.renderResponse();
    }

}
