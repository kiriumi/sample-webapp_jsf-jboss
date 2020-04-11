package producer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Singleton
@Startup
public class SqlSessionProducer {

	private Map<String, SqlSessionFactory> sqlSessionFactoryCash = new HashMap<>();
	private String defaultId;

	@PostConstruct
	public void initialize() throws IOException {
		loadDefaultEnvironment();
	}

	private void loadDefaultEnvironment() {

		try (InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml")) {

			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// デフォルトのenvironmentを読み込む
			SqlSessionFactory defaultEnvironmentFactory = builder.build(in);
			Configuration con = defaultEnvironmentFactory.getConfiguration();
			defaultId = con.getEnvironment().getId();
			sqlSessionFactoryCash.put(defaultId, defaultEnvironmentFactory);

		} catch (IOException e) {
			System.out.println(e);

		}
	}

	private void loadEnvironment(String environment) {

		try (InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml")) {

			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// デフォルト以外のenvironmentを読み込む
			SqlSessionFactory factory = builder.build(in, environment);
			sqlSessionFactoryCash.put(environment, factory);

		} catch (IOException e) {

		}
	}

	@Produces
	@RequestScoped
	public SqlSession openSession() {
		return sqlSessionFactoryCash.get(defaultId).openSession();
	}

	public void closeSession(@Disposes SqlSession sqlSession) {
		sqlSession.close();
	}

}