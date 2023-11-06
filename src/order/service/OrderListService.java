package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;
import sheet.dao.SheetDao;

public class OrderListService {
	private OrderDao orderDao = new OrderDao();
	private SheetDao sheetDao = new SheetDao();
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

	public Integer updateOrderProgress(int orderNo, String progress) {
		// TODO Auto-generated method stub
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedOrder = orderDao.update(conn, orderNo, progress);
			
			if (savedOrder < 0) {
				throw new RuntimeException("fail to update order");
			}
			conn.commit();
			return savedOrder;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Integer updateSheetProgress(int orderNo, String progress) {
		// TODO Auto-generated method stub
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedOrder = sheetDao.update(conn, orderNo, progress);
			
			if (savedOrder < 0) {
				throw new RuntimeException("fail to update sheet");
			}
			conn.commit();
			return savedOrder;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
