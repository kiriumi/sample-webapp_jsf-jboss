package log;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaEclipselinkLogger extends AbstractSessionLog {

    @Override
    public void log(final SessionLogEntry sessionLogEntry) {

        if (sessionLogEntry.getLevel() < SessionLog.FINER) {
            return;
        }

        log.debug(sessionLogEntry.getMessage());
    }

}
