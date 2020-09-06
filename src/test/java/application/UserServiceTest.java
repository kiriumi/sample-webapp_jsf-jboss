package application;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.UserService;
import dto.User;
import repository.RoleMapper;
import repository.UserMapper;

class UserServiceTest {

    @Mock
    UserMapper userMapper;

    @Mock
    RoleMapper roleMapper;

    @Mock
    EntityManager entitiyManager;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        // Mocitoの場合必須
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("モックを使ったMyBatisのテスト")
    public class MyBatisMockTest {

        @Test
        @DisplayName("Eメールアドレスによるユーザ存在チェック")
        public void testHasUser() {

            User returnUser = new User();
            returnUser.setEmailaddress("hoge@com");
            returnUser.setLastName("hoge");

            when(userMapper.selectByPrimaryKey(returnUser.getEmailaddress())).thenReturn(returnUser);

            boolean hasUser = userService.hasUser("hoge@com");

            assertThat(hasUser, is(true));
        }
    }

    @Nested
    @DisplayName("モックを使ったJPAのテスト")
    public class JpaWithMockTest {

        @Test
        @DisplayName("Eメールアドレスによるユーザ取得のテスト")
        public void testFindByEmailAddressWithJpa() {

            User returnUser = new User();
            returnUser.setEmailaddress("hoge@com");
            returnUser.setLastName("hoge");

            when(entitiyManager.find(User.class, "hoge@com")).thenReturn(returnUser);

            User user = userService.findByEmailAddressWithJpa("hoge@com");

            assertAll("ユーザのテスト",
                    () -> assertThat(user.getEmailaddress(), is("hoge@com")),
                    () -> assertThat(user.getLastName(), is("hoge")));
        }

        @Test
        @DisplayName("Eメールアドレスによるユーザの存在チェック")
        public void testHasUserWithJpa() {

            when(entitiyManager.find(User.class, "")).thenReturn(null);

            boolean hasUser = userService.hasUserWithJpa("");

            assertThat(hasUser, is(false));
        }
    }

    @Test
    @DisplayName("例外発生のテスト")
    public void testThrowNullPointerException() {

        User returnUser = new User();
        returnUser.setEmailaddress("hoge@com");
        userService.findByEmailAddressWithJpa(null);

        NullPointerException nullEx = assertThrows(
                NullPointerException.class, () -> userService.throwNullPointerException());
        assertThat(nullEx.getMessage(), is("ぬるぽ"));
    }

}
