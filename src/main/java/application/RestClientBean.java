package application;

import java.util.Base64;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import domain.MessageService;
import dto.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import security.CustomPrincipal;

/**
 * REST送信クラス
 *
 * @author kengo
 *
 */
@Model
@Data
@EqualsAndHashCode(callSuper = false)
public class RestClientBean extends BaseBackingBean {

    private final UriBuilder baseRestUriBuilder = UriBuilder
            .fromUri("http://localhost:8080/sample-webapp-jsf/webresources");

    private String greetingMessage;

    private int responseStatus;

    private User user;

    @Inject
    private MessageService messageService;

    /**
     * GETメソッドであいさつ文を取得する
     * @return
     */
    public String greet() {

        try {

            Client client = ClientBuilder.newClient();

            // http://localhost:8080/sample-webapp-jsf/webresources/user-name/ほげ?age=17
            UriBuilder userNameUri = baseRestUriBuilder.path("user-name").path("ほげ").queryParam("age", 17);

            String greetingMessage = client.target(userNameUri).request(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.AUTHORIZATION, getBasicAuthHttpHeaderValue()).get(String.class);

            setGreetingMessage(greetingMessage);

        } catch (Exception e) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }

        return null;
    }

    /**
     * GETメソッドで名前を送り、JSONでユーザーを取得する
     *
     * @return
     */
    public String getUserWithJson() {

        Client client = ClientBuilder.newClient();

        // http://localhost:8080/sample-webapp-jsf/webresources/user/get?name=foo
        UriBuilder userGetUri = baseRestUriBuilder.path("user").path("get").queryParam("name", "foo");

        User user = client.target(userGetUri).request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, getBasicAuthHttpHeaderValue()).get(User.class);

        setUser(user);

        return null;
    }

    /**
     * POSTメソッドでユーザーを送信する
     *
     * @return
     */
    public String postUserWithJson() {

        Client client = ClientBuilder.newClient();

        User user = new User();
        user.setLastName("bar");
        user.setEmailaddress("bar@example.com");

        // http://localhost:8080/sample-webapp-jsf/webresources/user/post?name=bar
        UriBuilder userPostUri = baseRestUriBuilder.path("user").path("post").queryParam("name", user.getLastName());

        Response response = client.target(userPostUri)
                .request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getBasicAuthHttpHeaderValue())
                .post(Entity.json(user));

        setResponseStatus(response.getStatus());

        return null;
    }

    private String getBasicAuthHttpHeaderValue() {

        CustomPrincipal principal = (CustomPrincipal) FacesContext.getCurrentInstance().getExternalContext()
                .getUserPrincipal();

        String usernameAndPassword = String.join(":", principal.getEmailAddress(), principal.getPassword());
        String credentials = Base64.getEncoder().encodeToString(usernameAndPassword.getBytes());

        return "Basic " + credentials;
    }
}
