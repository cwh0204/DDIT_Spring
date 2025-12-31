package kr.or.ddit.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MapperProxyFactory {
	
	private static SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlsessionfactory();
	
	public static <T> T  generateProxy(Class<T> mapperType) {
		// Mapper 의 특정 메소드에 대해
		// 반복되는 보일러플레이트 코드를 해결하기 위한 템플릿을 갖고있음.
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				try(SqlSession sqlSession = factory.openSession()){
					T mapperProxy = sqlSession.getMapper(mapperType);
//					saved = mapperProxy.selectMember(username);
					Object result =  method.invoke(mapperProxy, args);
					sqlSession.commit();
					return result;
				}
			}
		};
		
		// 매퍼 인터페이스의 독적 프록시 객체 반환
		// proxy : 원본 객체의 동작을 대리할 수 있는 대리 객체
		// 프록시를 통해 어떤 메소드가 호출되면 해당 호출건에 대한 제어를 InvocatonHandler 가 맡는다.
		return (T) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class<?>[] {mapperType}, invocationHandler);
	}
}
