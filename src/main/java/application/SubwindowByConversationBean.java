package application;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@ConversationScoped
@Slf4j
public class SubwindowByConversationBean extends BaseBackingBean implements Serializable {

    @Getter
    @Setter
    private String parentToSub;

    @Getter
    @Setter
    private String subToParent;

    @Inject
    @Getter
    @Setter
    private Conversation conv;

    public void viewActionParent() {
        if (conv.isTransient()) {
            conv.begin("subwindow");
        }
        System.out.println("親画面の初期表示処理");
    }

    public void viewActionSub() {
        System.out.println("子画面の初期表示処理");
    }

    public String openSubwindow() throws IOException {
        return null;
    }

    public String closeSubwindow() {
        return null;
    }

    public String subwindowAction() {
        log.debug("サブウィンドウのアクション");
        return null;
    }

}
