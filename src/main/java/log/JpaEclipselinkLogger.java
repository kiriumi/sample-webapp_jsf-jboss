package log;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaEclipselinkLogger extends AbstractSessionLog {

    @Override
    public void log(final SessionLogEntry sessionLogEntry) {

        String nameSpace = sessionLogEntry.getNameSpace();

        if (nameSpace != null && nameSpace.equals("sql")) {
            log.debug(sessionLogEntry.getMessage());
        }
    }

}
