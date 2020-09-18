package transaction_token;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@ConversationScoped
public class TransactionTokener implements Serializable {

    @Inject
    private Conversation conversation;

    public void begin(final String namespace) {
        conversation.end();
        conversation.begin(namespace);
    }

    public boolean valid(final String namespace) {

        if (!namespace.equals(conversation.getId())) {
            return false;
        }

        if (conversation.isTransient()) {
            return false;
        }

        return true;
    }

    public void end() {
        conversation.end();
    }

}
