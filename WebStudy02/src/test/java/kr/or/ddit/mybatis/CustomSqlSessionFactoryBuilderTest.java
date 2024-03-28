package kr.or.ddit.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetSessionFactory() {
		SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSessionFactory();
		System.out.println(factory);
		try(
			//한 메서드 안에서만 지역코드 형태로 사용
			SqlSession session = factory.openSession();
		){
			System.out.println(session);
		}
	}

}
