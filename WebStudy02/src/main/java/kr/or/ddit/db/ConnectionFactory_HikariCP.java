package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * Factory Object[Method] Pattern
 * 	: 객체의 생성을 전담하는 객체를 별도 운영하는 구조
 *
 */
public class ConnectionFactory_HikariCP {
	
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	private static String driverClassName;

	//Hikari를 사용하여 특정 DB에대한 종속성을 해결했다
	static {
		
		try(
			InputStream is = ConnectionFactory_HikariCP.class.getResourceAsStream("./DbInfo.properties");
		) {
			Properties props = new Properties();
			props.load(is);
			HikariConfig config = new HikariConfig();			
			
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			
			driverClassName = props.getProperty("driverClassName");
			
			config.setDriverClassName(driverClassName);
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);

			config.setAutoCommit(true);
			
			//최대 풀링 개수
			config.setMaximumPoolSize(5);
			//최소 풀링개수
			config.setMinimumIdle(3);
			//커넥션이 없을때 기다리는 시간
			config.setConnectionTimeout(2000);
			//커넥션 테스트
			config.setConnectionTestQuery("select sysdate from dual");
			
			dataSource = new HikariDataSource(config);
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
