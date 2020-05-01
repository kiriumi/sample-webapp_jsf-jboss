package application;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import context.SystemDirContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;

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

    public void switchTestMode() {
        testMode.switchMode();
    }

    public void switchTraceMode() {
        traceMode.switchMode();
    }

    public String logout() {
        return "/application/logout.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void postConstruct() {
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
