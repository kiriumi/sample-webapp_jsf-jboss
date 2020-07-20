package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;

import org.primefaces.PrimeFaces;

import domain.DialogMessage.Level;
import lombok.Getter;

@SessionScoped
@Getter
public class DialogMessageService implements Serializable {

    private final List<DialogMessage> messages = new ArrayList<>();

    public void addMessage(final Level level, final String message) {

        if (messages.size() == 0) {
            showDialog();
        }
        messages.add(new DialogMessage(level, message));
    }

    private void showDialog() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("headerElement", "ほげ");
        PrimeFaces.current().dialog().openDynamic("/application/dialog-message", options, null);
    }

    public void clear() {
        messages.clear();
    }

}
