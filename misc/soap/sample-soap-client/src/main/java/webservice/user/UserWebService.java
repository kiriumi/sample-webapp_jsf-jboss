package webservice.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import dto.User;

@WebService(targetNamespace = "http://webservice/user")
public interface UserWebService {

    @WebMethod
    @WebResult(name = "greet") // このアノテーションがなくても、正常に動作する
    public String hello(final String name);

    @WebMethod
    @WebResult(name = "user")
    public User getUserByEmailAddress(@WebParam(name = "emailAddress") String emailAddress)
            throws WebServiceException;

    @WebMethod
    //    @Oneway // 戻り値がない場合、このアノテーションを使う（※なくても良い）
    public void addUser(final User user) throws WebServiceException;

}
