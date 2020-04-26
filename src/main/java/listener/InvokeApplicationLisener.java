package listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvokeApplicationLisener implements PhaseListener {

    private final Logger LOGGER = LogManager.getLogger();

    @Override
    public void beforePhase(final PhaseEvent event) {
        LOGGER.debug("アプリケーション実行リスナー：前処理");
    }

    @Override
    public void afterPhase(final PhaseEvent event) {
        LOGGER.debug("アプリケーション実行リスナー：後処理");
    }

    @Override
    public PhaseId getPhaseId() {
        // リスナーを実行したいフェーズを返す
        return PhaseId.INVOKE_APPLICATION;
    }

}
