package webservice;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.ws.Endpoint;

@Singleton
@Startup
public class HelloPublisher {

    private static final String SOAP_SERVER_URL = "http://localhost:8081/soap";

    @PostConstruct
    public void startSoapServer() {
        Endpoint.publish(SOAP_SERVER_URL, new HelloEndpoint());
    }

    //    // Javaアプリケーションとしてローカルで実行する場合
    //    public static void main(final String[] args) {
    //        Endpoint.publish(SOAP_SERVER_URL, new SampleEndpoint());
    //    }
}
