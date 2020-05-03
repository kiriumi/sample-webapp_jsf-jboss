package webservice.greet;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice/greet")
public interface GreetingWebService {

    @WebMethod
    public String goodMorning(final String name);

    @WebMethod
    public String hello(final String name);
}
