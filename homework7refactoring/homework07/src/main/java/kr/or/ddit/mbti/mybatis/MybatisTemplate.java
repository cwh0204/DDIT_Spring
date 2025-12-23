package kr.or.ddit.mbti.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisTemplate {
	
	private static SqlSessionFactory factory = CustomSqlSessionFactoryBulder.getSqlsessionfactory();
	
	public static <T> T  generateProxy(Class<T> mapperType) {

		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				try(SqlSession sqlSession = factory.openSession()){
					T mapperProxy = sqlSession.getMapper(mapperType);
					Object result =  method.invoke(mapperProxy, args);
					sqlSession.commit();
					return result;
				}
			}
		};

		return (T) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class<?>[] {mapperType}, invocationHandler);
	}
}
