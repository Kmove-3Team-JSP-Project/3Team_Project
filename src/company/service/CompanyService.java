package company.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import company.dao.CompanyDao;
import company.model.Company;
import jdbc.connection.ConnectionProvider;

public class CompanyService {
	private CompanyDao companyDao = new CompanyDao();
	private int size = 10;

	public CompanyPage getCompany(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = companyDao.getCount(conn);
			List<Company> content = companyDao.select(conn, (pageNum - 1) * size, size);
			return new CompanyPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
