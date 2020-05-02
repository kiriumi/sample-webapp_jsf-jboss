package webservice;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.ws.Endpoint;

import lombok.extern.slf4j.Slf4j;
import webservice.greet.GreetingEndpoint;
import webservice.user.UserEndpoint;

@Singleton
@Startup
@Slf4j
public class SoapPublisher {

    private final String baseSoapUrl = "http://localhost:8081/soap/";

    @PostConstruct
    public void startSoapService() {
        startServer(baseSoapUrl + "greeting", new GreetingEndpoint());
        startServer(baseSoapUrl + "user", new UserEndpoint());
    }

    private void startServer(final String soapUrl, final Object endpoint) {

        Endpoint.publish(soapUrl, endpoint);
        log.info("SOAPサーバの開始:{}", soapUrl);
    }

}
