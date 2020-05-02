package soap;

import webservice.HelloEndpoint;
import webservice.HelloEndpointService;

public class SoapClient {

    public static void main(final String[] args) {

        HelloEndpointService service = new HelloEndpointService();
        HelloEndpoint endpoint = service.getHelloEndpointPort();

        System.out.println(endpoint.hello("SOAP"));
    }

}
