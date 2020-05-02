package webservice.greet;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class GreetingEndpoint {

    @WebMethod
    public String goodMorning(final String name) {
        return "おはよう！" + name;
    }

    @WebMethod
    public String hello(final String name) {
        return "こんちは！" + name;
    }

    @WebMethod
    public String goodEvening(final String name) {
        return "こばわ！" + name;
    }

    @WebMethod
    public String goodNight(final String name) {
        return "おやすみー！" + name;
    }

}
