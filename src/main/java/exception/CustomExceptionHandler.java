package exception;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.inject.Inject;

import domain.TwoFactorAuthenticator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    @Inject
    TwoFactorAuthenticator authenticator;

    private final ExceptionHandler wrapped;

    @SuppressWarnings("deprecation")
    public CustomExceptionHandler(final ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() {

        for (Iterator<ExceptionQueuedEvent> queuedEvent = getUnhandledExceptionQueuedEvents().iterator(); queuedEvent
                .hasNext() == true;) {

            ExceptionQueuedEventContext eventContext = queuedEvent.next().getContext();
            Throwable throwable = getRootCause(eventContext.getException()).getCause(); // アプリケーション例外を取得
            if (throwable == null) {
                throwable = eventContext.getException();
            }

            // メッセージを設定
            FacesContext facesContext = eventContext.getContext();
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "システム障害", throwable.getMessage()));
            facesContext.getExternalContext().getFlash().setKeepMessages(true); // リダイレクトしてもFacesMessageが消えないように設定

            try {
                // エラー画面に遷移
                String contextPath = facesContext.getExternalContext().getRequestContextPath();

                facesContext.getExternalContext().redirect(contextPath + "/error-system.xhtml");

            } catch (IOException e) {
                log.error(e.getMessage());

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
