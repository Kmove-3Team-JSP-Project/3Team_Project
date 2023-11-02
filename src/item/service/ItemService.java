package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemService {
	private ItemDao itemDao = new ItemDao();
	private int size = 30;

	public ItemPage getItemPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = itemDao.getCount(conn);
			List<Item> content = itemDao.select(conn, (pageNum - 1) * size, size);

			content.sort((item1, item2) -> Integer.compare(item1.getItem_Id(), item2.getItem_Id()));
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to get ItemPage", e);
		}
	}
}