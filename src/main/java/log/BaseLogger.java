package log;

import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public abstract class BaseLogger {

    private final String logMessagePropBaseName = "log-messages";

    private Logger logger;

    private int stackTraceIndex;

    public void debug(final String key, final Object... params) {
        logger.debug(getLogMessage(key), params);
    }

    public void info(final String key, final Object... params) {
        logger.info(getLogMessage(key), params);
    }

    public void warn(final String key, final Object... params) {
        logger.warn(getLogMessage(key), params);
    }

    public void error(final String key, final Object... params) {
        logger.error(getLogMessage(key), params);
    }

    protected void initLogger(final Class<?> loggerClass, final int stackTraceIndex) {
        this.logger = LoggerFactory.getLogger(loggerClass);
        this.stackTraceIndex = stackTraceIndex;
    }

    private String getLogMessage(final String key) {

        ResourceBundle logMessageProp = ResourceBundle.getBundle(logMessagePropBaseName);
        String logMessage = null;

        try {
            logMessage = logMessageProp.getString(key);
        } catch (Exception e) {
            // 該当のキーが見つからない場合、空文字を返すため
            logger.warn("該当のキーがないよ：{}", key);
        }

        putCalledClassInfo();

        return Optional.ofNullable(logMessage).orElse("");
    }

    protected void putCalledClassInfo() {

        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        if (stackTraces.length <= stackTraceIndex + 1) {
            return;
        }

        StackTraceElement stackTrace = stackTraces[stackTraceIndex];

        MDC.put("class", stackTrace.getClassName());
        MDC.put("method", stackTrace.getMethodName());
    }

}
