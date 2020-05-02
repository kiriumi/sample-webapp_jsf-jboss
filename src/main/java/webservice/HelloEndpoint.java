package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloEndpoint {

    @WebMethod
    public String hello(final String name) {
        return "Hello " + name;
    }

}
