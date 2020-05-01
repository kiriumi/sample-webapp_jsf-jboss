package application;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

import context.SystemDirContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;

@Model // @Named＋@RequestScoped
@Slf4j
public class CommonBean extends BaseBackingBean {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    @Getter
    private SystemDirContext systemDirContext;

    @Inject
    private TestMode testMode;

    @Inject
    private TraceMode traceMode;

    public void switchTestMode() {
        testMode.switchMode();
    }

    public void switchTraceMode() {
        traceMode.switchMode();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return redirect("logout");
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        log.debug("");
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("");
    }

    public void viewAction() {
        log.debug("");
    }

    public void preRender() {
        log.debug("");
    }

}
