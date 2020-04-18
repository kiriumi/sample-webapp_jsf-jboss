package webresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("user/{name}")
public class UserPathResource {

	/**
	 * 以下URLにGETすると、実行する
	 * http://localhost:8080/sample-webapp-jsf/webresources/user/ほげ
	 *
	 * @param name 名前
	 * @return 君の名前は、ほげ
	 */
	@GET
	public String getUserName(@PathParam("name") final String name) {
		return "君の名前は、" + name;
	}

	/**
	 * 以下URLにGETすると、実行する
	 * http://localhost:8080/sample-webapp-jsf/webresources/user/ふー?age=17
	 *
	 * @param name 名前
	 * @param age 年齢
	 * @return 君の名前は、ふーで、年齢は17だね
	 */
	@GET
	public String getUserNameWithAge(@PathParam("name") final String name, @QueryParam("age") final String age) {
		return "君の名前は、" + name + "で、年齢は" + age + "だね";
	}

}
