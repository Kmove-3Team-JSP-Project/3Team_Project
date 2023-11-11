package recall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import recall.dao.RecallDao;
import recall.model.Recall;

public class RecallListService {
	private RecallDao recallDao = new RecallDao();
	private int size = 10;

	public RecallPage getRecallPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = recallDao.selectCount(conn);
			List<Recall> content = recallDao.select(conn, (pageNum - 1) * size, size);
			return new RecallPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Integer updateRecallProcess(int recallNo, String process) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedRecall = recallDao.update(conn, recallNo, process);

			if (savedRecall < 0) {
				throw new RuntimeException("fail to update recall");
			}
			conn.commit();
			return savedRecall;
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
