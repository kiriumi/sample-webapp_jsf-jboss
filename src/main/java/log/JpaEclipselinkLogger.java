package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

public class JpaEclipselinkLogger extends AbstractSessionLog {

    public static final String LOGGER_NAME = "log.JpaEclipselinkLog";

    private static final Logger logger = LogManager.getLogger(LOGGER_NAME);

    @Override
    public void log(final SessionLogEntry sessionLogEntry) {

        if (sessionLogEntry.getLevel() < SessionLog.FINER) {
            return;
        }
        logger.debug(sessionLogEntry.getMessage());
    }

}
