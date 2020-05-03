package webresources;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.UserService;
import dto.User;

/**
 * REST受信するクラス
 *
 * @author kengo
 *
 */
@Path("user")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("get")
    @Produces("application/json")
    public User getUserByName(@QueryParam("name") final String name) {

        User user = new User();
        user.setName(name);
        user.setEmailaddress(name + "@example.com");

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
    public User postByJson(final User user, @QueryParam("name") final String name) {

        userService.toString();
        user.setUpdatedtime(LocalDateTime.now().toString());

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
    public User postByXml(final User user) {

        user.setUpdatedtime(LocalDateTime.now().toString());
        return user;
    }

}
