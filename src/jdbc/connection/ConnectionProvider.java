package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException { // 커넥션을 구할때 사용
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:inventory"); // poolName=board에서 가져옴
	}
}
