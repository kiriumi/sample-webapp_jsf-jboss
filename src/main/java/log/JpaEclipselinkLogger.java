package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

public class JpaEclipselinkLogger extends AbstractSessionLog {

    private static final Logger LOGGER = LogManager.getLogger("log.JpaEclipselinkLog");

    @Override
    public void log(final SessionLogEntry sessionLogEntry) {

        if (sessionLogEntry.getLevel() < SessionLog.FINER) {
            return;
        }
        LOGGER.debug(sessionLogEntry.getMessage());
    }

}
