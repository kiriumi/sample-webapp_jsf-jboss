package application;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import lombok.Data;

@Data
public abstract class AbstractManagedBean implements Serializable {

	protected String infoMessage;

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

	//	protected void setErrorMessage(final String errorMessage) {
	//
	//		FacesContext context = FacesContext.getCurrentInstance();
	//
	//		FacesMessage message = new FacesMessage(errorMessage);
	//		message.setSeverity(FacesMessage.SEVERITY_ERROR);
	//		context.addMessage(component.getClientId(), message);
	//		context.renderResponse();
	//	}

}
