package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;



/**
 * 
 * Factory Object[Method] Pattern
 * 	: 객체의 생성을 전담하는 객체를 별도 운영하는 구조
 *
 */
public class ConnectionFactory_JDBC_Ver3 {
	
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	private static PooledConnection pc;

	static {
		
		try(
			InputStream is = ConnectionFactory_JDBC_Ver3.class.getResourceAsStream("./DbInfo.properties");
		) {
			Properties props = new Properties();
			props.load(is);
			OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
			dataSource = ocpds;
			
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");	
			
			ocpds.setURL(url);
			ocpds.setUser(user);
			ocpds.setPassword(password);

			//일정 개수의 커넥션 생성
			pc = ocpds.getPooledConnection();
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = pc.getConnection();
		return conn;
	}
}
