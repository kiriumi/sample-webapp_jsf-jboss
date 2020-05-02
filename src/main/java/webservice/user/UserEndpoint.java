package webservice.user;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.UserService;
import dto.User;

@WebService
public class UserEndpoint {

    @Inject // 注入できない（nullになる）
    UserService userService;

    @WebMethod
    public String tellNameByEmailAddress(final String emailAddress) {

        User user = userService.findByEmailAddressWithJpa(emailAddress);
        return "あなたの名前は「" + user.getName() + "」だよ";
    }

    @WebMethod
    public User setEmailAddress(final User user) {

        user.setEmailaddress(user.getName() + "@com");
        return user;
    }

}
