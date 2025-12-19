package kr.or.ddit.mbti.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBulder {
	private final static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "kr/or/ddit/mybatis/Configuration.xml";

		try (Reader reader = Resources.getResourceAsReader(resource)) {

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}
	public static SqlSessionFactory getSqlsessionfactory() {
		return sqlSessionFactory;
	}
}
