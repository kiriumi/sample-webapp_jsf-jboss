package domain;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import dto.Hoge;
import dto.User;
import dto.UserExample;
import mapper.UserMapper;

@Stateless
public class UserService {

	@Inject
	private SqlSession sqlSession;

	@Inject
	private Hoge hoge;

	public boolean exists(Integer id) {

		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(1);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.selectByExample(example);

		return users.size() > 0;

//		hoge.getHoge();

//		return true;
	}

}
