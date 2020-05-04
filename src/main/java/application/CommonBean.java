package application;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import context.SystemDirContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;
import security.AviableValidator;
import security.LoginLogout;

@Model // @Named＋@RequestScoped
@Slf4j
public class CommonBean {

    @Inject
    private ExternalContext externalContext;

    @Inject
    @Getter
    private SystemDirContext systemDirContext;

    @Inject
    private TestMode testMode;

    @Inject
    private TraceMode traceMode;

    @Inject
    AviableValidator aviableValidator;

    @Getter
    private boolean aviable;

    public void switchTestMode() {
        testMode.switchMode();
    }

    public void switchTraceMode() {
        traceMode.switchMode();
    }

    @LoginLogout
    public String logout() {
        externalContext.invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("");
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("");
    }

    public String viewAction() {

        log.debug("");

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.getUserPrincipal();

        if (request.getUserPrincipal() == null) {
            return "login.xhtml?faces-redirect=true";
        }

        return null;
    }

    public void preRender() {
        this.aviable = aviableValidator.available();
        log.debug("");
    }

}
