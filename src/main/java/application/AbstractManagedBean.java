package application;

import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import context.TestMode;
import context.TraceMode;
import dto.User;
import interceptor.Logging;
import lombok.Getter;
import lombok.Setter;

@Logging
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

    private final Logger LOGGER = LogManager.getLogger();

    /**
     * 管理Beanを初期化する際、実行する処理処理
     */
    @PostConstruct
    public void doPreConstruct() {
        LOGGER.debug("preConstruct");
        preConstruct();
    }

    public void preRender() {
        testMode.setRollbackOnly();
        LOGGER.debug("preRender");
    }

    public void viewAction() {
        LOGGER.debug("viewAction");
    }

    /**
     * 管理Beanを破棄する際、実行する処理
     */
    @PreDestroy
    public void doFin() {
        fin();
        testMode.setRollbackOnly();
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
