package mode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import log.JpaEclipselinkLogger;
import lombok.extern.slf4j.Slf4j;

@SessionScoped
@Slf4j
public class TraceMode implements Serializable {

    private boolean trace = false;

    private final LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
    private final Configuration configuration = loggerContext.getConfiguration();

    private final List<String> targetLoggerNames = Arrays.asList("mapper", JpaEclipselinkLogger.class.getName(),
            "model");

    public void switchMode() {
        this.trace = trace ? false : true;
        changeLogLevel();
        log.debug("トレースモードを切り替えたよ：{}", trace);
    }

    private void changeLogLevel() {
        targetLoggerNames.forEach(loggerName -> updateLogger(loggerName));
    }

    private void updateLogger(final String loggerName) {

        LoggerConfig loggerConfig = configuration.getLoggerConfig(loggerName);
        loggerConfig.setLevel(trace ? Level.DEBUG : Level.INFO);
        loggerContext.updateLoggers();
    }

}
