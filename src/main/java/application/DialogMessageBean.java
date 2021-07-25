package application;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import domain.DialogMessage;
import domain.DialogMessageService;
import lombok.Getter;

@Named
@ViewScoped
public class DialogMessageBean implements Serializable {

    @Inject
    DialogMessageService dialogMessageService;

    @Getter
    private List<DialogMessage> messages;

    @PostConstruct
    public void init() {
        this.messages = dialogMessageService.getMessages();
    }

    @PreDestroy
    public void clear() {
        dialogMessageService.clear();
        this.messages.clear();
    }

}
