package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {

	private MemberDao memberDao = new MemberDao(); // MemberDao 객체를 생성

	public User login(int id, String password) { // 사용자 로그인을 처리하는 메서드
		try (Connection conn = ConnectionProvider.getConnection()) { // 사용자 ID로 데이터베이스에서 사용자 정보를 가져옴
			Member member = memberDao.selectById(conn, id);
			if (member == null) { // 사용자 정보가 없는 경우(멤버 값이 null일 경우) 로그인 실패 예외를 던짐
				throw new LoginFailException();
			}
			if (!member.matchPassword(password)) { // 입력된 패스워드와 데이터베이스에서 가져온 패스워드를 비교하여 일치하지 않으면 로그인 실패 예외를 던짐
				throw new LoginFailException();
			}
			return new User(member.getMemberId(), member.getName()); // 로그인에 성공한 경우 User 객체를 생성하고 반환
		} catch (SQLException e) { // SQL 오류가 발생한 경우 런타임 예외로 전환
			throw new RuntimeException(e);
		}
	}
}