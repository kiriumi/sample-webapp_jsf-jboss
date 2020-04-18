package webresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("ping")
public class PingPongResource {

	/**
	 * 以下URLで実行される
	 * http://localhost:8080/sample-webapp-jsf/webresources/ping
	 * @return
	 */
	@GET
	public String pong() {
		return "Pong";
	}
}
