package listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvokeApplicationLisener implements PhaseListener {

    @Override
    public void beforePhase(final PhaseEvent event) {
        log.debug("アプリケーション実行リスナー：前処理");
    }

    @Override
    public void afterPhase(final PhaseEvent event) {
        log.debug("アプリケーション実行リスナー：後処理");
    }

    @Override
    public PhaseId getPhaseId() {
        // リスナーを実行したいフェーズを返す
        return PhaseId.INVOKE_APPLICATION;
    }

}
