package application;

import java.util.ArrayList;
import java.util.HashSet;

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
import security.AviableTimeValidator;
import security.LoginLogout;
import security.RoleAuthorizator;

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
    private RoleAuthorizator roleAuther;

    @Inject
    AviableTimeValidator aviableValidator;

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

    public void preRender() {
        log.debug("");
    }

    public boolean aviableTime() {
        return aviableValidator.available();

    }

    public boolean permittedRoles(final HashSet<String> roles) {
        return roleAuther.authUserIn(new ArrayList<String>(roles));
    }

    public boolean forbidenRoles(final HashSet<String> roles) {
        return !permittedRoles(roles);
    }
}
