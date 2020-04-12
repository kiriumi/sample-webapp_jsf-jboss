package domain;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import dto.User;
import dto.UserExample;
import mapper.UserMapper;

@Dependent
public class UserService extends User {

	@Inject
	private SqlSession sqlSession;

	public boolean find(final String emailAddress, final String password) {

		UserExample example = new UserExample();
		example.createCriteria().andEmailaddressEqualTo(emailAddress);
		example.createCriteria().andPasswordEqualTo(password);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.selectByExample(example);

		return users.size() > 0;
	}

	public boolean exists(final String emailAddress) {

		UserExample example = new UserExample();
		example.createCriteria().andEmailaddressEqualTo(emailAddress);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.selectByExample(example);

		return users.size() > 0;
	}

	public void addUser(final User user) {

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.insert(user);
	}

}
