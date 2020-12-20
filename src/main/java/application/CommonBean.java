package application;

import java.util.ArrayList;
import java.util.HashSet;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.servlet.ServletException;

import context.WebApplicationContext;
import domain.MessageService;
import lombok.extern.slf4j.Slf4j;
import mode.TestMode;
import mode.TraceMode;
import security.AviableTimeValidator;
import security.RoleAuthorizator;
import security.custom.CustomPrincipal;

/**
 * 共通処理クラス
 *
 * @author kengo
 *
 */
@Model // @Named＋@RequestScoped
@Slf4j
public class CommonBean {

    @Inject
    ExternalContext externalContext;

    @Inject
    WebApplicationContext appContext;

    @Inject
    CustomPrincipal principal;

    @Inject
    private MessageService messageService;

    @Inject
    private RoleAuthorizator roleAuther;

    @Inject
    AviableTimeValidator aviableValidator;

    @Inject
    private TestMode testMode;

    @Inject
    private TraceMode traceMode;

    public void preRender() {

        log.debug("");

        if (!aviableTime()) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR, "利用時間外だよ");
            log.debug("利用時間外だよ");
        }
    }

    public boolean permittedRoles(final HashSet<String> roles) {
        return roleAuther.authUserIn(new ArrayList<String>(roles));
    }

    public boolean forbidenRoles(final HashSet<String> roles) {
        return !permittedRoles(roles);
    }

    public void logout() throws AuthenticationException {

        try {
            appContext.request().logout();

        } catch (ServletException e) {
            throw new AuthenticationException("ログアウトに失敗したよ", e);
        }

        principal.setLogined(false);
        externalContext.invalidateSession();
    }

    public boolean aviableTime() {
        return aviableValidator.available();

    }

    public void switchTestMode() {
        testMode.switchMode();
    }

    public void switchTraceMode() {
        traceMode.switchMode();
    }
}
