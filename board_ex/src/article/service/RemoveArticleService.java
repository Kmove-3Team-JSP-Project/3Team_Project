package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class RemoveArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public void remove(RemoveRequest remReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 게시글 번호에 해당하는 Article 객체를 구한 후
			// 해당 번호 게시글이 존재하지 않으면 예외 발생
			Article article = articleDao.selectById(conn, remReq.getArticleNumber());
			if (article == null) {
				throw new ArticleContentNotFoundException();
			}
			// 삭제 가능 검사
			if (!canRemove(remReq.getUserid(), article)) {
				throw new PermissionDeniedException();
			}
			// 두 Dao의 delete() 메서드를 이용해 삭제
			articleDao.delete(conn, remReq.getArticleNumber());
			contentDao.delete(conn, remReq.getArticleNumber());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 게시글 검사 기능 구현
	// 사용자 ID 와 작성자 ID 가 같으면 true 리턴
	private boolean canRemove(String modfyingUserId, Article article) {
		return article.getWriter().getId().equals(modfyingUserId);
	}
}
