package item.service;

import java.sql.Connection;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemService {
	
	private ItemDao itemDao = new ItemDao();
	private int size = 30;
	
	public ItemPage getItemPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int startRow = (pageNum, - 1) * size;
			int total = itemDao.select(conn, startRow, size);
			
		
		
		}

}
