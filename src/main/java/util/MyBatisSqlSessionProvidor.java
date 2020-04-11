package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionProvidor {

	//	 @AppleDataSource
	@Inject
	private SqlSessionFactory appleSessionFactory;

	private SqlSessionFactory create(String environment) {

		try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {

			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			return builder.build(stream, environment);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/*
	 * SqlSessionFactoryのインスタンスをCDIに登録
	 * DataSourceを複数利用しているため、`@AppleDataSource`と`@PineappleDataSource`というannotationで識別している
	 * `SqlSessionFactory`はアプリケーションが起動している間はインスタンスを破棄しないために`@ApplicationScoped`を指定する
	 */
	@ApplicationScoped
	//	    @AppleDataSource
	@Produces
	public SqlSessionFactory appleSessionFactory() {
		return this.create("apple");
	}

	/*
	 * `SqlSession`をインジェクションするためのメソッド
	 * Request毎にopen/closeするため`@RequestScoped`を指定
	 * `@Produces`でCDI経由でインスタンスを取得できるようになる
	 */
	@RequestScoped
	//	    @AppleDataSource
	@Produces
	public SqlSession appleSession() {
		return appleSessionFactory.openSession();
	}

	/*
	 * Requestが完了したときに`SqlSession`をcloseする
	 */
	public void closeAppleSession(@Disposes SqlSession sqlSession) {
		sqlSession.close();
	}

}
