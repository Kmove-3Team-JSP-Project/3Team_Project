package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockService {
	private StockDao stockDao = new StockDao();
	private int size = 30;

	public StockPage getStockPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = stockDao.getCount(conn);
			List<Stock> content = stockDao.select(conn, (pageNum - 1) * size, size);

			content.sort((stock1, stock2) -> Integer.compare(stock1.getStock_Cord(), stock2.getStock_Cord()));
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to get StockPage", e);
		}
	}
}