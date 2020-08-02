package domain;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import dto.Role;
import dto.RoleExample;
import dto.User;
import dto.UserExample;
import dto.User_;
import mapper.RoleMapper;
import mapper.UserMapper;

@RequestScoped
public class UserService {

    @Inject
    private SqlSession sqlSession;

    //    @Inject
    //    UserMapper userMapper; // mybatis-cdiの残骸

    //    @Inject
    //    RoleMapper roleMapper;

    public User find(final String emailAddress, final String password) {

        UserExample example = new UserExample();
        example.createCriteria().andEmailaddressEqualTo(emailAddress).andPasswordEqualTo(password);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByExample(example);

        return users.size() == 1 ? users.get(0) : null;
    }

    public boolean authenticate(final String emailAddress, final String password) {

        UserExample example = new UserExample();
        example.createCriteria().andEmailaddressEqualTo(emailAddress).andPasswordEqualTo(password);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByExample(example);

        return users.size() == 1;
    }

    public boolean hasUser(final String emailAddress) {

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(emailAddress);

        return user == null || StringUtils.isBlank(user.getEmailaddress()) ? false : true;
    }

    public List<String> getRolesOnly(final User user) {

        RoleExample example = new RoleExample();
        example.createCriteria().andEmailaddressEqualTo(user.getEmailaddress());

        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = roleMapper.selectByExample(example);

        List<String> rolesOnly = new ArrayList<>();
        roles.forEach(role -> rolesOnly.add(role.getRole()));
        return rolesOnly;
    }

    public List<String> getRoles(final String emailaddress) {

        RoleExample example = new RoleExample();
        example.createCriteria().andEmailaddressEqualTo(emailaddress);

        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = roleMapper.selectByExample(example);

        List<String> rolesOnly = new ArrayList<>();
        roles.forEach(role -> rolesOnly.add(role.getRole()));
        return rolesOnly;
    }

    public void addUser(final User user, final List<String> roles) {

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(user);

        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        roles.forEach(role -> roleMapper.insert((new Role(user.getEmailaddress(), role))));
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

    public void addUserWithJpa(final User user, final List<String> roles) {
        entitiyManager.persist(user);
        roles.forEach(role -> entitiyManager.persist(new Role(user.getEmailaddress(), role)));
    }

    @SuppressWarnings("unchecked")
    public List<User> searchByJpql(final String emailaddress, final String lastName, final String firstName) {

        String queryString = "SELECT user FROM User user WHERE %s user.lastName LIKE :lastName AND user.firstName LIKE :firstName ORDER BY user.emailaddress";
        Query query;

        if (StringUtils.isBlank(emailaddress)) {
            query = entitiyManager.createQuery(String.format(queryString, ""), User.class)
                    .setParameter("lastName", StringUtils.join("%", lastName, "%"))
                    .setParameter("firstName", StringUtils.join("%", firstName, "%"));
        } else {
            query = entitiyManager
                    .createQuery(String.format(queryString, "user.emailaddress = :emailaddress AND"), User.class)
                    .setParameter("emailaddress", emailaddress)
                    .setParameter("lastName", StringUtils.join("%", lastName, "%"))
                    .setParameter("firstName", StringUtils.join("%", firstName, "%"));
        }

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> searchBySql(final String emailaddress, final String lastName, final String firstName) {

        String queryString = "SELECT u.* FROM public.user u WHERE %s u.last_name LIKE ?2 ORDER BY u.emailaddress";
        Query query;

        if (StringUtils.isBlank(emailaddress)) {
            query = entitiyManager.createNativeQuery(String.format(queryString, ""), User.class)
                    .setParameter(2, StringUtils.join("%", lastName, "%"))
                    .setParameter(3, StringUtils.join("%", firstName, "%"));
        } else {
            query = entitiyManager.createNativeQuery(String.format(queryString, "u.emailaddress = ?1 AND"), User.class)
                    .setParameter(1, emailaddress)
                    .setParameter(2, StringUtils.join("%", lastName, "%"))
                    .setParameter(3, StringUtils.join("%", firstName, "%"));
        }

        return query.getResultList();
    }

    public List<User> serchByCriteriaApi(final String emailaddress, final String lastName, final String firstName) {

        CriteriaBuilder builder = entitiyManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        List<Predicate> creteria = new ArrayList<Predicate>();

        if (StringUtils.isNoneBlank(emailaddress)) {
            creteria.add(builder.equal(from.get(User_.emailaddress), emailaddress));
        }
        if (StringUtils.isNoneBlank(lastName)) {
            creteria.add(builder.like(from.get(User_.lastName), "%" + lastName + "%"));
        }
        if (StringUtils.isNoneBlank(firstName)) {
            creteria.add(builder.like(from.get(User_.firstName), "%" + firstName + "%"));
        }

        query.select(from).where(
                builder.and(creteria.toArray(new Predicate[] {})))
                .orderBy(builder.asc(from.get(User_.emailaddress)));

        return entitiyManager.createQuery(query).getResultList();

        //        // 検索条件が静的な場合
        //        // テーブル項目の指定に、メタモデルを使用する場合
        //        query.select(from).where(
        //                builder.equal(from.get(User_.emailaddress), emailaddress),
        //                builder.like(from.get(User_.lastName), "%" + lastName + "%"),
        //                builder.like(from.get(User_.firstName), "%" + lastName + "%"))
        //                .orderBy(builder.asc(from.get(User_.emailaddress)));
        //
        //        // テーブル項目の指定に、文字列を使用する場合
        //        query.select(from).where(
        //                builder.equal(from.get("emailaddress"), emailaddress),
        //                builder.like(from.get("lastName"), "%" + lastName + "%"),
        //                builder.like(from.get("firstName"), "%" + firstName + "%"))
        //                .orderBy(builder.asc(from.get("emailaddress")));
    }

    @SuppressWarnings("unchecked")
    public List<User> searchWithRolesByJpql(final String emailaddress, final String lastName) {

        Query query = entitiyManager.createQuery(
                "SELECT user FROM User user INNER JOIN user.roles roles WHERE user.emailaddress = :emailaddress AND user.lastName LIKE :lastName ORDER BY user.emailaddress",
                User.class)
                .setParameter("emailaddress", emailaddress)
                .setParameter("lastName", StringUtils.join("%", lastName, "%"));

        return query.getResultList();
    }

    public List<User> serchWithRoleByCriteriaApi(final String emailaddress, final String lastName) {

        CriteriaBuilder builder = entitiyManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        //        Join<User, Role> join =  from.join(

        // テーブル項目の指定に、メタモデル（クラス名_）を使用する場合
        query.select(from).where(builder.equal(from.get(User_.emailaddress), emailaddress),
                builder.like(from.get(User_.lastName), "%" + lastName + "%"))
                .orderBy(builder.asc(from.get(User_.emailaddress)));

        return entitiyManager.createQuery(query).getResultList();
    }

    public void deleteUser(User user) {

        if (!entitiyManager.contains(user)) {
            // Entityが永続化コンテキストの管理外にある場合、削除できないため、mergeして管理下に置く
            // 参考：https://www.monotalk.xyz/blog/Entity-must-be-managed-to-call-remove-in-eclipselink/
            user = entitiyManager.merge(user);
        }
        entitiyManager.remove(user);
    }

    public void deleteUsers(final List<User> users) {
        // メソッド参照：オブジェクト::メソッド
        users.forEach(this::deleteUser);
    }

    public void throwNullPointerException() throws NullPointerException {
        throw new NullPointerException("ぬるぽ");
    }

}
