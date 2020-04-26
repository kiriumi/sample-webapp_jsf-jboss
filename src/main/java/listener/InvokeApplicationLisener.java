package listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class InvokeApplicationLisener implements PhaseListener {

    @Override
    public void afterPhase(final PhaseEvent event) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    public void beforePhase(final PhaseEvent event) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    public PhaseId getPhaseId() {
        // リスナーを実行したいフェーズを返す
        return PhaseId.INVOKE_APPLICATION;
    }

}
