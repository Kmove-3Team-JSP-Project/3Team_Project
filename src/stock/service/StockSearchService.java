package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockSearchService {
	private StockDao stockDao = new StockDao();
	private int size = 30;

	public StockPage searchStockByStock(StockSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = stockDao.getCountByStock(conn, searchRequest.getSearchTerm());
			List<Stock> content = stockDao.selectByStock(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search Stocks by stock", e);
		}
	}

	public StockPage searchStockByStorage(StockSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = stockDao.getCountByStorage(conn, searchRequest.getSearchTerm());
			List<Stock> content = stockDao.selectByStorage(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search Stocks by storage", e);
		}
	}
}
