package token;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

public class TokenUpdateListener implements PhaseListener {

    @Inject
    private Token token;

    @Inject
    private ChildToken childToken;

    @Override
    public void afterPhase(final PhaseEvent event) {

        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = facesCtx.getExternalContext();
        ResourceBundle prop = ResourceBundle.getBundle("ApplicationConfig");

        if (facesCtx.isPostback() || extCtx.getRequestServletPath().endsWith(prop.getString("error.page"))) {
            // 初期表示処理以外またはエラー画面の場合、何もしない
            return;
        }

        Map<String, Object> session = extCtx.getSessionMap();

        // トークンを更新
        if (TokenUtils.isParent()) {
            // 親画面の場合
            String updatedToken = generateToken();
            session.put("token", updatedToken); // セッションにトークンを設定
            token.setToken(updatedToken); // 画面にトークンを設定

        } else {
            // 子画面の場合
            String updatedToken = generateToken();
            String namespace = extCtx.getRequestParameterMap().get(TokenUtils.KEY_CHILD_TOKEN_NAMESPACE);

            @SuppressWarnings("unchecked")
            Map<String, String> childTokenMap = (Map<String, String>) session.get(TokenUtils.KEY_CHILD_TOKEN_MAP);
            childTokenMap.put(namespace, updatedToken); // セッションにトークンを設定

            // 画面にトークンを設定
            childToken.setNamespace(namespace);
            childToken.setToken(updatedToken);
        }

    }

    @Override
    public void beforePhase(final PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.APPLY_REQUEST_VALUES;
    }

    private String generateToken() {
        long seed = new Random().nextLong() + new Date().getTime();
        return DigestUtils.sha256Hex(Long.toString(seed)).substring(0, 32);
    }

}
