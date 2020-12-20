package listener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import security.custom.CustomPrincipal;

@Slf4j
public class LoginCheckLisener implements PhaseListener {

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    CustomPrincipal principal;

    private static final String securedPageContextPath = "/%s/";

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {

        ResourceBundle properties = ResourceBundle.getBundle("env");
        List<String> scuredPageContexts = Arrays.asList(properties.getString("secured.page.contexts").split(","));
        String loginPage = properties.getString("login.page");

        if (!isSecuredPage(scuredPageContexts)) {
            return;
        }

        // 認証済みか判定
        if (externalContext.getSession(false) == null || !principal.isLogined()) {

            try {
                String loginPagePath = StringUtils.joinWith("/", externalContext.getRequestContextPath(), loginPage);
                externalContext.redirect(loginPagePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isSecuredPage(List<String> scuredPageContexts) {

        for (String scuredPageContext : scuredPageContexts) {
            if (externalContext.getRequestServletPath()
                    .startsWith(String.format(securedPageContextPath, scuredPageContext.trim()))) {
                // 未認証で表示できる画面の場合、何もしない
                return true;
            }
        }
        return false;
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
