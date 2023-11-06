package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public int autoNo(Connection conn) throws SQLException {
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn
				.prepareStatement("SELECT COUNT(*) FROM member")) {
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return 0;
		}
	}

	public Member selectById(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// member 테이블에서 memberid 값이 id 파라메터와 동일한 데이터 조회
			pstmt = conn.prepareStatement("select * from member where member_id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) { // 같은 데이터가 존재하면 member객체 생성
				member = new Member(rs.getInt("member_id"), rs.getString("name"), rs.getString("password"),
						rs.getString("mail"), rs.getString("position"));
			}
			return member; // 같은 데이터가 없으면 null 반환
		} finally {
			// TODO: handle finally clause
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// member 테이블에 데이터 추가
	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into member values (?,?,?,?,?)")) {
			pstmt.setInt(1, mem.getMemberId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setString(4, mem.getMail());
			pstmt.setString(5, mem.getPosition());

			pstmt.executeUpdate();
		}
	}

	// member 테이블 데이터 수정을 위한 메서드
	// member.getId()가 같은 레코드의 name, password 필드 값을 변경
	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update member set name = ?, password = ?, mail = ?, position = ? where member_id = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getPosition());
			pstmt.setInt(5, member.getMemberId());
			pstmt.executeUpdate();
		}
	}

}
