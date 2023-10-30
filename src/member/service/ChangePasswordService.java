package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();

	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			// MemberDao의 selectById()를 이용해 joinReq.getId()에 해당하는 회원 데이터를 구함
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, userId);
			// 가입하려는 ID와 같은 데이터가 존재하면 MemberNotFoundException 발생
			if (member == null) {
				throw new MemberNotFoundException();
			}
			if (!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			//
			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit(); // 트랜잭션 커밋
		} catch (SQLException e) { // SQLException 발생시 트랜잭션 롤백, RuntimeException 발생
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn); // 커넥션 종료
		}
	}
}
