package application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインページクラス
 *
 * @author Kengo
 *
 */
@Named
@RequestScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginSccessBean extends AbstractManagedBean {

	private String message;

	@Override
	public void init() {
		setInfoMessage((String) getFlash().get("loginSccessMessage"));
	}

}
