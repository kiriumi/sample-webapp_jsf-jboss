package context;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class TestMode implements Serializable {

	private boolean test;

	@EJB
	private EjbContextProvider contextProvider;

	public void switchMode() {
		this.test = test ? false : true;
	}

	public void setTransaction() {

		if (test) {
			contextProvider.getSessionContext().setRollbackOnly();
		}
	}
}
