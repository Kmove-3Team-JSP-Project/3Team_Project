package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemSearchService {
	private ItemDao itemDao = new ItemDao();

	public ItemPage searchItems(String searchType, String searchTerm, int pageNumber) {
		List<Item> searchResult = null;

		try (Connection conn = ConnectionProvider.getConnection()) {
			if ("item_name".equals(searchType)) {
				searchResult = itemDao.selectByName(conn, searchTerm);
			} else if ("item_class".equals(searchType)) {
				searchResult = itemDao.selectByClass(conn, searchTerm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (searchResult == null) {
			searchResult = new ArrayList<>();
		}

		
		int totalItems = searchResult.size();
		ItemPage itemPage = new ItemPage(totalItems, pageNumber, searchResult);

		return itemPage;
	}
}