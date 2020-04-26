package context;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class EjbContextProvider {

	@Resource
	private SessionContext sessionContext;
}
