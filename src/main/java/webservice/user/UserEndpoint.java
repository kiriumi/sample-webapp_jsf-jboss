package webservice.user;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.UserService;
import dto.User;

@WebService
public class UserEndpoint {

    @Inject // ポートが異なる（＝Webアプリケーション外）ため、Injectできない（nullになる）
    UserService userService;

    @WebMethod
    public String tellNameByEmailAddress(final String emailAddress) {

        User user = userService.findByEmailAddressWithJpa(emailAddress);
        return "あなたの名前は「" + user + "」だよ";
    }

}
