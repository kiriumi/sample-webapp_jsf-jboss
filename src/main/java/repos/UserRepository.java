package repos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.User;

public class UserRepository {

	@PersistenceContext
	private EntityManager entitiyManager;

	public User findByEmailAddress(final String emailAddress) {
		return entitiyManager.find(User.class, emailAddress);
	}

	public void addUser(final User user) {
		entitiyManager.persist(user);
	}
}
