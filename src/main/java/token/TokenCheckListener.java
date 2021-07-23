package token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class TokenCheckListener implements PhaseListener {

    @Override
    public void afterPhase(final PhaseEvent event) {
    }

    @Override
    public void beforePhase(final PhaseEvent event) {

        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = facesCtx.getExternalContext();

        // トークンチェック対象の画面か判定
        ResourceBundle prop = ResourceBundle.getBundle("ApplicationConfig");
        String tokenCheckPages = prop.getString("token.check.pages");
        List<String> tokenCheckPagesList = Arrays.asList(tokenCheckPages.split(","));

        if (!tokenCheckPagesList.stream().map(page -> page.trim())
                .anyMatch(page -> page.endsWith(extCtx.getRequestServletPath()))) {
            //トークンチェック対象の画面ではない場合、何もしない
            return;
        }

        if (facesCtx.isPostback()) {
            // 初期表示処理以外の場合、何もしない
            return;
        }

        // セッションの取得
        Map<String, Object> session = extCtx.getSessionMap();
        @SuppressWarnings("unchecked")
        Map<String, String> childTokenMap = (Map<String, String>) session.get(TokenUtils.KEY_CHILD_TOKEN_MAP);
        if (childTokenMap == null) {
            childTokenMap = new HashMap<>();
            session.put("childTokenMap", childTokenMap);
        }

        // トークンチェックを実施
        if (TokenUtils.isParent()) {

            // 親画面の場合
            childTokenMap.clear(); // 子画面のトークンをリセット

            String tokenInSession = (String) session.get(TokenUtils.KEY_TOKEN);
            String tokenInRequest = extCtx.getRequestParameterMap().get(TokenUtils.KEY_TOKEN);

            if (tokenInSession != null && !tokenInSession.equals(tokenInRequest)) {
                // トークンチェック不正の場合
                session.remove(TokenUtils.KEY_TOKEN);
                throw new RuntimeException("トークンチェック不正");
            }

        } else {

            // 子画面の場合
            String namespace = extCtx.getRequestParameterMap().get(TokenUtils.KEY_CHILD_TOKEN_NAMESPACE);
            String tokenInRequest = extCtx.getRequestParameterMap().get(TokenUtils.KEY_CHILD_TOKEN);

            if (tokenInRequest == null) {
                return; // トークンがない場合、初回アクセスのため何もしない
            }

            String tokenInSession = childTokenMap.get(namespace);

            if (tokenInSession == null || !tokenInSession.equals(tokenInRequest)) {
                childTokenMap.remove(namespace);
                throw new RuntimeException("トークンチェック不正");
            }
        }

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
