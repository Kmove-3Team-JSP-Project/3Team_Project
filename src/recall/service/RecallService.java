package recall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import recall.dao.RecallDao;
import recall.model.Recall;

public class RecallService {
	private RecallDao recallDao = new RecallDao();
	private int size = 30;

	public RecallPage getRecallPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = recallDao.getCount(conn);
			List<Recall> content = recallDao.select(conn, (pageNum - 1) * size, size);

			content.sort((recall1, recall2) -> Integer.compare(recall1.getRecall_No(), recall2.getRecall_No()));
			return new RecallPage(total, pageNum, content);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to get RecallPage. Cause: " + e.getMessage(), e);
		}
	}
}
