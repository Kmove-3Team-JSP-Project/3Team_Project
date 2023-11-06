package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import erp.dao.OrderDao;
import erp.model.Order;
import jdbc.connection.ConnectionProvider;

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

	public boolean updateOrderProgress(int orderNo, String progress) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		
		if(orderDao.updateSheetTrigger(conn)>0) {
			orderDao.update(conn, orderNo, progress);
			orderDao.dropTrigger(conn);
			
		}
		
		return false;
	}

}
