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

	// 페이징 처리된 RecallPage 객체를 반환하는 메서드
	public RecallPage getRecallPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 데이터베이스에서 전체 Recall 레코드 수를 조회
			int total = recallDao.getCount(conn);

			// 데이터베이스에서 특정 페이지의 Recall 레코드 목록을 조회
			List<Recall> content = recallDao.select(conn, (pageNum - 1) * size, size);

			// Recall 객체 리스트를 Recall_No를 기준으로 정렬
			content.sort((recall1, recall2) -> Integer.compare(recall1.getRecall_No(), recall2.getRecall_No()));

			// RecallPage 객체를 생성하여 반환
			return new RecallPage(total, pageNum, content);
		} catch (SQLException e) {
			// 예외 발생 시 런타임 예외로 처리하고 에러 메시지 포함
			throw new RuntimeException("Failed to get RecallPage. Cause: " + e.getMessage(), e);
		}
	}
}