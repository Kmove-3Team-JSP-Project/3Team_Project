package company.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import company.dao.CompanyDao;
import company.model.Company;
import jdbc.connection.ConnectionProvider;

public class CompanySearchService {
	private CompanyDao companyDao = new CompanyDao();
	private int size = 30;

	public CompanyPage searchItemsByName(CompanySearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = companyDao.getCountByName(conn, searchRequest.getSearchTerm());
			List<Company> content = companyDao.selectByName(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new CompanyPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by name", e);
		}
	}

	public CompanyPage searchCompaniesByClass(CompanySearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = companyDao.getCountByClass(conn, searchRequest.getSearchTerm());
			List<Company> content = companyDao.selectByClass(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new CompanyPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by class", e);
		}
	}

}
