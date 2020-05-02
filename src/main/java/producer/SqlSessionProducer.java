package producer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class SqlSessionProducer {

    private static String MYBATIS_CONFIG_PATH = "mybatis/mybatis-config.xml";

    private final Map<String, SqlSessionFactory> sqlSessionFactoryCash = new HashMap<>();

    private enum Environment {
        postgres, sqlite,
    }

    @PostConstruct
    public void init() {
        Arrays.asList(Environment.values())
                .forEach(environment -> loadSessionSqlFactory(environment.name()));
    }

    @Produces
    @RequestScoped // 接続プールが満杯になるのを防止するため、リクエストごとにオープン・クローズする
    public SqlSession openSession() {
        return sqlSessionFactoryCash.get(Environment.postgres.name()).openSession();
    }

    public void closeSession(@Disposes final SqlSession sqlSession) {
        sqlSession.close();
    }

    @Produces
    @RequestScoped
    @SqliteQualifier // Sqlite用とJavaが判断するための印（限定子）
    public SqlSession openSqliteSession() {
        return sqlSessionFactoryCash.get(Environment.sqlite.name()).openSession();
    }

    public void closeSqliteSession(@Disposes @SqliteQualifier final SqlSession sqlSession) {
        sqlSession.close();
    }

    private void loadSessionSqlFactory(final String environment) {

        SqlSessionFactory sqlSessionFactory = null;

        try (InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH)) {

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = builder.build(inputStream, environment);
            sqlSessionFactoryCash.put(environment, sqlSessionFactory);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
