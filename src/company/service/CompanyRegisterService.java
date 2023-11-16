package company.service;

import java.sql.Connection;
import java.sql.SQLException;

import company.dao.CompanyDao;
import company.model.Company;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class CompanyRegisterService {
	private CompanyDao companyDao = new CompanyDao();

	public int getCompanyNo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int companyNo = companyDao.getCompanyNo(conn);
			conn.commit();
			return companyNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			return -1;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Integer register(Company req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Company company = req;
			Company savedCompany = companyDao.insert(conn, company);

			if (savedCompany == null) {
				throw new RuntimeException("fail to insert order");
			}
			conn.commit();
			return savedCompany.getCompany_No();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
