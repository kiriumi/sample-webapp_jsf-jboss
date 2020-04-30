package domain;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import dto.User;
import dto.UserExample;
import mapper.UserMapper;

@Dependent
public class UserService {

    @Inject
    private SqlSession sqlSession;

    public User find(final String emailAddress, final String password) {

        UserExample example = new UserExample();
        example.createCriteria().andEmailaddressEqualTo(emailAddress);
        example.createCriteria().andPasswordEqualTo(password);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectByExample(example);

        return users.size() == 1 ? users.get(0) : null;
    }

    public boolean hasUser(final String emailAddress) {

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(emailAddress);

        return user == null || StringUtils.isBlank(user.getEmailaddress()) ? false : true;
    }

    public void addUser(final User user) {

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insert(user);
    }

    // ↑MyBatis
    // ----------------
    // ↓JPA

    @PersistenceContext(unitName = "sampleUnit")
    private EntityManager entitiyManager;

    public User findByEmailAddressWithJpa(final String emailAddress) {
        return entitiyManager.find(User.class, emailAddress);
    }

    public boolean hasUserWithJpa(final String emailAddress) {

        User user = entitiyManager.find(User.class, emailAddress);
        return user == null || StringUtils.isBlank(user.getEmailaddress()) ? false : true;
    }

    public void addUserWithJpa(final User user) {
        entitiyManager.persist(user);
    }

}
