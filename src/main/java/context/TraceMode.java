package context;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import log.JpaEclipselinkLogger;

@SessionScoped
public class TraceMode implements Serializable {

    private boolean trace = false;

    public void switchMode() {
        this.trace = trace ? false : true;
        changeLogLevel();
    }

    private void changeLogLevel() {

        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Configuration configuration = loggerContext.getConfiguration();
        LoggerConfig loggerConfig = configuration.getLoggerConfig(JpaEclipselinkLogger.LOGGER_NAME);

        loggerConfig.setLevel(trace ? Level.TRACE : Level.INFO);
        loggerContext.updateLoggers();
    }

}
