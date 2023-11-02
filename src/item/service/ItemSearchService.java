package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemSearchService {
	private ItemDao itemDao = new ItemDao();
	private int size = 30;

	public ItemPage searchItemsByName(ItemSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = itemDao.getCountByName(conn, searchRequest.getSearchTerm());
			List<Item> content = itemDao.selectByName(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size, size);
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by name", e);
		}
	}

	public ItemPage searchItemsByClass(ItemSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = itemDao.getCountByClass(conn, searchRequest.getSearchTerm());
			List<Item> content = itemDao.selectByClass(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size, size);
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to search items by class", e);
		}
	}
}
