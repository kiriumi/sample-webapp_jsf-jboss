package webservice.user;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import domain.UserService;
import dto.User;

@WebService(targetNamespace = "http://webservice/user", serviceName = "UserWebService", portName = "UserWebPort", endpointInterface = "webservice.user.UserWebService")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING) // 何もつけない場合は「SOAP1.1」になる
public class UserWebServiceImpl implements UserWebService {

    @Resource
    private WebServiceContext webServiceContext;

    @Inject
    UserService userService;

    @Override
    public String hello(final String name) {
        return "こんちは " + name;
    }

    @Override
    public User getUserByEmailAddress(final String emailAddress) throws WebServiceException {
        return userService.findByEmailAddressWithJpa(emailAddress);
    }

    @Override
    public void addUser(final User user) throws WebServiceException {
        userService.addUser(user);
    }

}
