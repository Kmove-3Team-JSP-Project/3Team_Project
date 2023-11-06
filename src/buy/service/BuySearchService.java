package buy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import buy.dao.BuyDao;
import buy.model.Buy;
import company.service.CompanySearchRequest;
import jdbc.connection.ConnectionProvider;

public class BuySearchService {
	private BuyDao buyDao = new BuyDao();
	private int size = 30;

	public BuyPage searchItemsByName(BuySearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = buyDao.getCountByName(conn, searchRequest.getSearchTerm());
			List<Buy> content = buyDao.selectByName(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new BuyPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by name", e);
		}
	}

	public BuyPage searchCompaniesByMember(BuySearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = buyDao.getCountByMember(conn, searchRequest.getSearchTerm());
			List<Buy> content = buyDao.selectByClass(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new BuyPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by class", e);
		}
	}

}
