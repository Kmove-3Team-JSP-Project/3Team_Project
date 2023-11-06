package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

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

	public Map<String, Integer> getItemNamesWithUnitPrice() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            Map<String, Integer> itemDetails = orderDao.getItemNamesWithUnitPrice(conn);
            conn.commit();
            return itemDetails;
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }


	public List<String> getCompanyList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> companyList = orderDao.getCompanyList(conn);
			conn.commit();
			return companyList;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getStorageList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> storageList = orderDao.getStorageList(conn);
			conn.commit();
			return storageList;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getAllItemNames() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> itemNames = orderDao.getAllItemNames(conn);
			conn.commit();
			return itemNames;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	

}
