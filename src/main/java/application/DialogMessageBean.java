package application;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import domain.DialogMessage;
import domain.DialogMessageService;
import inject.ViewModel;
import lombok.Getter;

@ViewModel
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
