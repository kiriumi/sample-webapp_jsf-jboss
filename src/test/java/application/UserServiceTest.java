package application;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.UserService;
import dto.User;

class UserServiceTest {

    @Mock
    SqlSession sqlSession;

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
                    () -> assertThat(user.getEmailaddress(), is("fail@com")),
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
