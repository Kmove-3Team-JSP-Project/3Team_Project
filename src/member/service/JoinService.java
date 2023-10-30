package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {

	private MemberDao memberDao = new MemberDao();

	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			// MemberDao의 selectById()를 이용해 joinReq.getId()에 해당하는 회원 데이터를 구함
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, joinReq.getId());
			// 가입하려는 ID와 같은 데이터가 존재하면 트랜잭션 롤백, DuplicateIdException 발생
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			// joinReq를 이용해 Member 객체 생셩, memberDao.insert()로 회원 데이터를 테이블에 삽입
			memberDao.insert(conn, 
					new Member(
							joinReq.getId(), 
							joinReq.getName(), 
							joinReq.getPassword(), 
							new Date())
					);
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
