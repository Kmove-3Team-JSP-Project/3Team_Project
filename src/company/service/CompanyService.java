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

	public CompanyPage getCompanyPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = companyDao.selectCount(conn);
			List<Company> content = companyDao.select(conn, (pageNum - 1) * size, size);
			return new CompanyPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerCompany(String company_No, String company_Name, String master, String phone,
			String myStorage) {
		// TODO Auto-generated method stub
		
	}

}
