package buy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import buy.dao.BuyDao;
import buy.model.Buy;
import jdbc.connection.ConnectionProvider;

public class BuyService {
	private BuyDao buyDao = new BuyDao();
	private int size = 10;

	public BuyPage getBuyPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = buyDao.selectCount(conn);
			List<Buy> content = buyDao.select(conn, (pageNum - 1) * size, size);
			return new BuyPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
