package exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyExceptionHandler extends ExceptionHandlerWrapper {

    @Override
    public void handle() throws FacesException {

        final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();

        while (queue.hasNext()) {

            ExceptionQueuedEvent item = queue.next();
            ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) item.getSource();

            try {
                Throwable throwable = exceptionQueuedEventContext.getException();
                log.error(throwable.getMessage(), throwable);

                FacesContext context = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
                NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();

                requestMap.put("error-message", throwable.getMessage());
                requestMap.put("error-stack", throwable.getStackTrace());

                navigationHandler.handleNavigation(context, null, "/error");
                context.renderResponse();

            } finally {
                queue.remove();
            }
        }
    }

}
