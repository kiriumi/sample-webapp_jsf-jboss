package exception;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(final ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() {

        for (Iterator<ExceptionQueuedEvent> queuedEvent = getUnhandledExceptionQueuedEvents().iterator(); queuedEvent
                .hasNext() == true;) {

            ExceptionQueuedEventContext eventContext = queuedEvent.next().getContext();
            Throwable throwable = getRootCause(eventContext.getException()).getCause(); // アプリケーション例外を取得

            // メッセージを設定
            FacesContext facesContext = eventContext.getContext();
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "システム障害", throwable.getMessage()));
            facesContext.getExternalContext().getFlash().setKeepMessages(true); // リダイレクトしてもFacesMessageが消えないように設定

            try {
                // エラー画面に遷移
                String contextPath = facesContext.getExternalContext().getRequestContextPath();
                facesContext.getExternalContext().redirect(contextPath + "/application/error-system.xhtml");

            } catch (IOException e) {
                log.error("/application/error.xhtml がないよ");

            } finally {
                queuedEvent.remove();
            }
        }
        wrapped.handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
}
