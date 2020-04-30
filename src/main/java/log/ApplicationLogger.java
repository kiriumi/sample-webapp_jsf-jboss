package log;

import javax.enterprise.context.Dependent;

@Dependent
public class ApplicationLogger extends BaseLogger {

    public ApplicationLogger() {
        initLogger(ApplicationLogger.class, 4);
    }
}
