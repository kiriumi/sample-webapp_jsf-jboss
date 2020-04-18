package webresources;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.UserService;
import model.UserEntity;

/**
 * REST受信するクラス
 *
 * @author kengo
 *
 */
@RequestScoped
@Path("user")
public class UserResource {

	@Inject
	UserService userService;

	@GET
	@Path("get")
	@Produces("application/json")
	public UserEntity getUserByName(@QueryParam("name") final String name) {

		UserEntity user = new UserEntity();
		user.setName(name);
		user.setEmailAddress(name + "@example.com");

		return user;
	}

	/**
	 * 以下URL，JSONでPOSTすると実行され、JSONでユーザーを返す
	 * http://localhost:8080/sample-webapp-jsf/webresources/user/foo
	 *
	 * { "emailAddress":"foo@com", "name":"foo" }
	 *
	 * @param user
	 * @param json
	 * @return
	 * {
	 *   "createdTime": null,
	 *   "updateTime": "2020-04-18T10:03:26.216",
	 *   "emailAddress": "foo@com",
	 *   "name": "foo",
	 *   "password": null
	 * }
	 */
	@POST
	@Path("post")
	@Consumes("application/json")
	@Produces("application/json")
	public UserEntity postByJson(final UserEntity user, @QueryParam("name") final String name) {

		userService.toString();
		user.setUpdateTime(LocalDateTime.now().toString());

		return user;
	}

	/**
	 * 以下URL，XMLでPOSTすると実行され、XMLでユーザーを返す
	 * http://localhost:8080/sample-webapp-jsf/webresources/user
	 *
	 * <user>
	 *     <emailAddress>hoge@com</emailAddress>
	 *     <name>hoge</name>
	 * </user>
	 *
	 * @param user
	 * @return
	 * <user>
	 *     <updateTime>2020-04-18T09:53:23.509</updateTime>
	 *     <emailAddress>hoge@com</emailAddress>
	 *    <name>hoge</name>
	 * </user>
	 */
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public UserEntity postByXml(final UserEntity user) {

		user.setUpdateTime(LocalDateTime.now().toString());
		return user;
	}

}
