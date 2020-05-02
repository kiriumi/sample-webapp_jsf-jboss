package soap;

import webservice.greet.GreetingEndpoint;
import webservice.greet.GreetingEndpointService;
import webservice.user.User;
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

        User user = new User();
        user.setName("hoge");

        User settedUser = userEndpoint.setEmailAddress(user);

        System.out.println(settedUser.getEmailaddress());

        // CDI管理BeanのUserを注入できないため、失敗する
        // System.out.println(userEndpoint.tellNameByEmailAddress("admin@hoge"));
    }
}
