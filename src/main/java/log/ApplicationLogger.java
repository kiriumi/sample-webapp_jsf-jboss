package log;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

import org.slf4j.MDC;

@Dependent
public class ApplicationLogger extends BaseLogger {

    @Override
    protected void putCustomInfo() {

        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

        MDC.put("contextPath", contextPath.substring(1)); // 頭に/があるため
    }
}
