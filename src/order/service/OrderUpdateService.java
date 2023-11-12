package order.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import sheet.dao.SheetDao;

public class OrderUpdateService {
	private OrderDao orderDao = new OrderDao();
	private SheetDao sheetDao = new SheetDao();
	
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
