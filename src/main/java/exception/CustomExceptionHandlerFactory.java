package exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory wrapped;

    @SuppressWarnings("deprecation")
    public CustomExceptionHandlerFactory(final ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {

        ExceptionHandler handler = new CustomExceptionHandler(wrapped.getExceptionHandler());
        return handler;
    }

}
