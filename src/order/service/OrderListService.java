package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

public class OrderListService {
	private OrderDao orderDao = new OrderDao();
	private int size = 10;
	
	public OrderPage getOrderPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = orderDao.selectCount(conn);
			List<Order> content = orderDao.select(conn, (pageNum-1) * size, size);
			return new OrderPage(total, pageNum, size, content);		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public OrderPage getOrderCheckPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = orderDao.selectCountProgress(conn);
			List<Order> content = orderDao.selectProgress(conn, (pageNum-1) * size, size);
			return new OrderPage(total, pageNum, size, content);		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public OrderPage getOrderSearchPage(String searchField, String searchText, int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = orderDao.selectCountSearch(conn, searchField, searchText);
			List<Order> content = orderDao.getSearch(conn, searchField, searchText, (pageNum-1) * size, size);
			return new OrderPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
