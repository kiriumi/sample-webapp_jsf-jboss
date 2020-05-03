package webservice.greet;

import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice/greet", serviceName = "GreetingWebService", portName = "GreetingWebPort", endpointInterface = "webservice.greet.GreetingWebService")
public class GreetingWebServiceImpl implements GreetingWebService {

    @Override
    public String goodMorning(final String name) {
        return "おはよう！" + name;
    }

    @Override
    public String hello(final String name) {
        return "こんちは！" + name;
    }

}
