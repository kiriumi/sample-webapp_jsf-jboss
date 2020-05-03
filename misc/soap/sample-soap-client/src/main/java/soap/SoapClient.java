package soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import dto.User;
import webservice.greet.GreetingWebService;
import webservice.user.UserWebService;

public class SoapClient {

    public static void main(final String[] args) throws MalformedURLException {

        GreetingWebService greetWebService = getEndpoint(
                "http://localhost:8080/sample-webapp-jsf/GreetingWebService?wsdl", GreetingWebService.class);

        System.out.println(greetWebService.goodMorning("ほげ"));
        System.out.println(greetWebService.hello("ふー"));

        // ----

        UserWebService userWebService = getEndpoint("http://localhost:8080/sample-webapp-jsf/UserWebService?wsdl",
                UserWebService.class);

        System.out.println(userWebService.hello("そーぷ"));
        System.out.println(userWebService.getUserByEmailAddress("admin@hoge").getName());

        User user = new User();
        user.setEmailaddress("soap@hoge");
        user.setName("soap");
        user.setPassword("soap");
        user.setCreatedtime(LocalDateTime.now().toString());
        user.setUpdatedtime(LocalDateTime.now().toString());

        userWebService.addUser(user);
    }

    private static <T> T getEndpoint(final String wsdlUrl, final Class<T> endpointInterface)
            throws MalformedURLException {

        WebService webServiceAnnotation = endpointInterface.getAnnotation(WebService.class);

        URL url = new URL(wsdlUrl);
        QName qName = new QName(webServiceAnnotation.targetNamespace(), endpointInterface.getSimpleName());
        Service service = Service.create(url, qName);

        return service.getPort(endpointInterface);
    }

}
