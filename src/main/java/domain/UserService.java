package domain;

import javax.enterprise.context.Dependent;

@Dependent
public class UserService {

	public boolean exists(Integer id) {
		return true;
	}

}

//@Dependent
//public class UserService {
//
//	@Inject
//	@Mapper
//	private UserMapper userMapper;
//
//	public boolean exists(Integer id) {
//
//		UserExample example = new UserExample();
//		example.createCriteria().andIdEqualTo(1);
//
//		List<User> users = userMapper.selectByExample(example);
//
//		return users.size() > 0;
//
//	}
//}

//@Dependent
//public class UserService {
//
//	@Inject
//	private SqlSession sqlSession;
//
//	public boolean exists(Integer id) {
//
//
//		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//		UserExample example = new UserExample();
//		example.createCriteria().andIdEqualTo(1);
//
//		List<User> users = userMapper.selectByExample(example);
//
//		return users.size() > 0;
//	}
//}
