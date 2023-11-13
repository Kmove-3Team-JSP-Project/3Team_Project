package recall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import recall.dao.RecallDao;
import recall.model.Recall;

public class RecallRegisterService {

	private RecallDao recallDao = new RecallDao();

	public int getRecallNo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int recallNo = recallDao.getRecallNo(conn);
			conn.commit();
			return recallNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			return -1;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Integer register(RecallRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Recall recall = toRecall(req);
			Recall savedRecall = recallDao.insert(conn, recall);

			if (savedRecall == null) {
				throw new RuntimeException("fail to insert recall");
			}
			conn.commit();
			return savedRecall.getRecall_No();
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

	private Recall toRecall(RecallRequest req) {
		return new Recall(req.getRecall_No(), req.getMember_Name(), req.getStorage_Name(), req.getStock_Name(),
				req.getUnit_Price(), req.getAmount(), req.getProcess_Date(), req.getProcess());
	}

	public Map<String, Integer> getStockNamesWithStockAmount() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Map<String, Integer> stockDetails1 = recallDao.getStockNamesWithStockAmount(conn);
			conn.commit();
			return stockDetails1;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);

		}
	}

	public Map<String, String> getStockNamesWithStorageNames() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Map<String, String> stockDetails2 = recallDao.getStockNamesWithStorageNames(conn);
			conn.commit();
			return stockDetails2;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getStockName() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> stockList = recallDao.getStockName(conn);
			conn.commit();
			return stockList;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Map<String, Integer> getItemNamesWithUnitPrice() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            Map<String, Integer> itemDetails = recallDao.getStockNamesWithUnitPrice(conn);
            conn.commit();
            return itemDetails;
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
	
}
