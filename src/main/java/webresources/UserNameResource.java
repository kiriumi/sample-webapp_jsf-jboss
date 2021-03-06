package webresources;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("user-name/{name}")
public class UserNameResource {

    /**
     * 以下URLにGETすると、実行する
     * http://localhost:8080/context/webresources/user-name/ほげ?age=17
     *
     * @param name 名前
     * @param age 年齢
     * @return
     */
    @GET
//    @RolesAllowed("admin")
    public String getGreetingMessageWithAge(@PathParam("name") final String name,
            @Min(0) @QueryParam("age") final Integer age) {

        return "ウチの名前は " + name + " で、年齢は " + age.toString() + " よ！";
    }

}
