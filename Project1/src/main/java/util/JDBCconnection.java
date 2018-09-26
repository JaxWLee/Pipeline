package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCconnection {
private static Connection conn;
	
	public static void main(String[] args) {
		conn = getConnection();
		System.out.println(conn);
		
	}
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
//				FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
//				Properties props = new Properties();
//				props.load(in);
//				
//				String url = props.getProperty("url");
//				String username = props.getProperty("username");
//				String password = props.getProperty("password");
				String url = "jdbc:oracle:thin:@dblee.cfwh0fkjhofh.us-east-2.rds.amazonaws.com:1521:ORCL";
				String username = "jaxon";
				String password = "Mr.Lee33";
				conn = DriverManager.getConnection(url, username, password );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
