package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener { //커넥션풀을 초기화 하기위함

	@Override
	public void contextInitialized(ServletContextEvent sce) { // 컨텍스트 리스너를 생성하기위한 ServletContextListener 인터페이스 구현하는 클래스
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig)); // "키=값" 형식의 문자열로부터 프로퍼티 로딩
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) { // jdbc드라이버를 불러오기 위함
		String dirverClass = prop.getProperty("jdbcdriver");
		try {
			Class.forName(dirverClass);
		} catch (ClassNotFoundException ex) {
			// TODO: handle exception
			throw new RuntimeException("fail to load JDBC Driver", ex); // 드라이버를 불러오지 못했을 경우 출력
		}
	}

	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");

			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			String validationQuery = prop.getProperty("validationQuery"); // 커넥션 검사 쿼리
			if(validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			int minIdle = getIntProperty(prop, "minIdle", 5); // 최소 유휴 커넥션
			poolConfig.setMinIdle(minIdle);
			int maxTotal = getIntProperty(prop, "maxTotal", 50); // 최대 크기
			poolConfig.setMaxTotal(maxTotal);

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory,
					poolConfig);
			poolableConnFactory.setPool(connectionPool);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if (value == null) return defaultValue;
		return Integer.parseInt(value);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
