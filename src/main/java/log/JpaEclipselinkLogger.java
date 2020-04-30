package log;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaEclipselinkLogger extends AbstractSessionLog {

    //    private final Logger logger = LogManager.getLogger(JpaEclipselinkLogger.class);

    @Override
    public void log(final SessionLogEntry sessionLogEntry) {

        if (sessionLogEntry.getLevel() < SessionLog.FINER) {
            return;
        }
        //        logger.debug(sessionLogEntry.getMessage());
        log.debug(sessionLogEntry.getMessage());
    }

}
