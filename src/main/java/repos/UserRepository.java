package repos;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import model.UserEntity;

@Dependent
public class UserRepository {

	@PersistenceContext(unitName = "sampleUnit")
	private EntityManager entitiyManager;

	public UserEntity findByEmailAddress(final String emailAddress) {
		return entitiyManager.find(UserEntity.class, emailAddress);
	}

	public boolean hasUser(final String emailAddress) {

		UserEntity user = entitiyManager.find(UserEntity.class, emailAddress);

		return StringUtils.isBlank(user.getEmailAddress()) ? false : true;
	}

	public void addUser(final UserEntity user) {
		entitiyManager.persist(user);
	}
}
