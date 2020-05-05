package log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class FacesLogBridgeListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent arg) {

        // 参考：https://mkyong.com/jsf2/jsf-2-log4j-integration-example/
        SLF4JBridgeHandler.install();
    }

}
