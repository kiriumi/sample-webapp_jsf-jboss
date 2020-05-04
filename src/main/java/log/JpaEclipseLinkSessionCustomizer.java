package log;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.sessions.Session;

public class JpaEclipseLinkSessionCustomizer implements SessionCustomizer {

    @Override
    public void customize(final Session session) throws Exception {

        JpaEclipselinkLogger jpaLogger = new JpaEclipselinkLogger();
        jpaLogger.setLevel(SessionLog.FINEST);
        session.setSessionLog(jpaLogger);
    }

}
