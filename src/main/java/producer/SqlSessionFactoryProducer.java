package producer;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class SqlSessionFactoryProducer {

    private static String MYBATIS_CONFIG_PATH = "mybatis/mybatis-config.xml";

    private enum Environment {
        postgres, sqlite,
    }

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    public SqlSessionFactory producePostgresFactory() throws IOException {
        return loadSqlSessionFactory(Environment.postgres);
    }

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    @SqliteQualifier
    public SqlSessionFactory produceSqliteFactory() throws IOException {
        return loadSqlSessionFactory(Environment.sqlite);
    }

    private SqlSessionFactory loadSqlSessionFactory(final Environment environment) throws IOException {

        InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream, environment.name());

        log.debug("MyBatisの設定情報を読み込んだよ：{}", environment.name());

        return sqlSessionFactory;
    }

}
