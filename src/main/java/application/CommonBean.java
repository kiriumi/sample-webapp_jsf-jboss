package application;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import context.SystemDirContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;
import security.AviableValidator;

@Model // @Named＋@RequestScoped
@Slf4j
public class CommonBean {

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
        this.aviable = aviableValidator.available();
        log.debug("");
    }

}
