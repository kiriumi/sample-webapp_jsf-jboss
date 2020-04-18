package repos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.UserEntity;

public class UserRepository {

	@PersistenceContext
	private EntityManager entitiyManager;

	public UserEntity findByEmailAddress(final String emailAddress) {
		return entitiyManager.find(UserEntity.class, emailAddress);
	}

	public void addUser(final UserEntity user) {
		entitiyManager.persist(user);
	}
}
