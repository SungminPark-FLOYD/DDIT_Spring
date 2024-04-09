package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	//static이 없으면 heap 영역에 새로운 인스턴스가 생성될때마다 호출된다
	static {
		String resource = "kr/or/ddit/mybatis/Configuration.xml";
		try(
			Reader reader = Resources.getResourceAsReader(resource);
		) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sqlSessionFactory;
	}
}
