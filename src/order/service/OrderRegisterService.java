package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import erp.dao.OrderDao;
import erp.model.Order;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class OrderRegisterService {

	private OrderDao orderDao = new OrderDao();

	public int getOrderNo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int orderNo = orderDao.getOrderNo(conn);
			conn.commit();
			return orderNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			return -1;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Integer register(OrderRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Order order = toOrder(req);
			Order savedOrder = orderDao.insert(conn, order);
			if (savedOrder == null) {
				throw new RuntimeException("fail to insert order");
			}
			conn.commit();
			return savedOrder.getOrderNo();
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

	private Order toOrder(OrderRequest req) {
		return new Order(req.getOrderNo(), req.getMemberName(), req.getItemName(), req.getUnitPrice(), req.getAmount(),
				req.getPrice(), req.getCompanyName(), req.getStorageName(), req.getOrderDate(), req.getProgress());
	}

	public List<String> getAllItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
