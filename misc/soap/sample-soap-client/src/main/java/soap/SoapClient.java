package soap;

import webservice.greet.GreetingEndpoint;
import webservice.greet.GreetingEndpointService;
import webservice.user.UserEndpoint;
import webservice.user.UserEndpointService;

public class SoapClient {

    public static void main(final String[] args) {

        GreetingEndpointService greetingService = new GreetingEndpointService();
        GreetingEndpoint greetingEndpoint = greetingService.getGreetingEndpointPort();

        System.out.println(greetingEndpoint.goodMorning("ほげ"));
        System.out.println(greetingEndpoint.hello("ふー"));

        UserEndpointService userService = new UserEndpointService();
        UserEndpoint userEndpoint = userService.getUserEndpointPort();

        System.out.println(userEndpoint.tellNameByEmailAddress("user@hoge"));
    }
}
