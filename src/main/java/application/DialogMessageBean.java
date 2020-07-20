package application;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import domain.DialogMessage;
import domain.DialogMessageService;
import lombok.Getter;

@Model
public class DialogMessageBean {

    @Inject
    DialogMessageService messageService;

    @Getter
    private List<DialogMessage> messages;

    @PostConstruct
    public void init() {
        this.messages = messageService.getMessages();
    }

    @PreDestroy
    public void clear() {
        messageService.clear();
        this.messages.clear();
    }

}
