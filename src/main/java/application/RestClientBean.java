package application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import entity.UserEntity;
import lombok.Data;

@Named
@RequestScoped
@Data
public class RestClientBean {

	private UriBuilder userBaseUriBuilder = UriBuilder
			.fromUri("http://localhost:8080/sample-webapp-jsf/webresources/user");

	private String resultGet;

	private UserEntity user;

	private int responseStatus;

	public String getUserName() {

		Client client = ClientBuilder.newClient();

		String result = client.target("http://localhost:8080/sample-webapp-jsf/webresources/user-name/ほげ")
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);

		setResultGet(result);

		return null;
	}

	public String getUseByJson() {

		Client client = ClientBuilder.newClient();

		UserEntity userEntity = client.target("http://localhost:8080/sample-webapp-jsf/webresources/user/get?name=foo")
				.request(MediaType.APPLICATION_JSON)
				.get(UserEntity.class);

		setUser(userEntity);

		return null;
	}

	public String postUserByJson() {

		Client client = ClientBuilder.newClient();

		UserEntity user = new UserEntity();
		user.setName("bar");
		user.setEmailAddress("bar@example.com");

		// http://localhost:8080/sample-webapp-jsf/webresources/user/post?name=bar
		UriBuilder userUri = userBaseUriBuilder.path("post").queryParam("name", user.getName());

		Response response = client.target(userUri)
				.request(MediaType.APPLICATION_JSON).post(Entity.json(user));

		setResponseStatus(response.getStatus());

		return null;
	}
}
