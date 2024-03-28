package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 * 
 * Factory Object[Method] Pattern
 * 	: 객체의 생성을 전담하는 객체를 별도 운영하는 구조
 *
 */
public class ConnectionFactory_JDBC_Ver1 {
	
	private static String url;
	private static String user;
	private static String password;

	//드라이버를 로딩하는 위치가 달라서 드라이버가 언제 로딩되는지 알 수 없다
	//커넥션 풀링을 안쓰기 때문에 사용량이 늘어나면 커넥션에 과부하가 일어난다
	static {
		
		try(
			InputStream is = ConnectionFactory_JDBC_Ver1.class.getResourceAsStream("./DbInfo.properties");
		) {
			Properties props = new Properties();
			props.load(is);
			//드라이버의 경로 메모리에 로딩해주기
			Class.forName(props.getProperty("driverClassName"));
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
