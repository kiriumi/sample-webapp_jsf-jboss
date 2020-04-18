package application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import lombok.Data;
import model.UserEntity;

/**
 * REST送信するクラス
 *
 * @author kengo
 *
 */
@Named
@RequestScoped
@Data
public class RestClientBean {

	private UriBuilder baseRestUriBuilder = UriBuilder.fromUri("http://localhost:8080/sample-webapp-jsf/webresources");

	private String greetingMessage;

	private UserEntity user;

	private int responseStatus;

	/**
	 * GETメソッドであいさつ文を取得する
	 * @return
	 */
	public String greet() {

		Client client = ClientBuilder.newClient();

		// http://localhost:8080/sample-webapp-jsf/webresources/user-name/ほげ
		UriBuilder userNameUri = baseRestUriBuilder.path("user-name").path("ほげ").queryParam("age", 17);

		String greetingMessage = client.target(userNameUri).request(MediaType.TEXT_PLAIN).get(String.class);
		setGreetingMessage(greetingMessage);

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

		UserEntity userEntity = client.target(userGetUri).request(MediaType.APPLICATION_JSON).get(UserEntity.class);

		setUser(userEntity);

		return null;
	}

	/**
	 * POSTメソッドでユーザーを送信する
	 *
	 * @return
	 */
	public String postUserWithJson() {

		Client client = ClientBuilder.newClient();

		UserEntity user = new UserEntity();
		user.setName("bar");
		user.setEmailAddress("bar@example.com");

		// http://localhost:8080/sample-webapp-jsf/webresources/user/post?name=bar
		UriBuilder userPostUri = baseRestUriBuilder.path("user").path("post").queryParam("name", user.getName());

		Response response = client.target(userPostUri)
				.request(MediaType.APPLICATION_JSON).post(Entity.json(user));

		setResponseStatus(response.getStatus());

		return null;
	}
}
