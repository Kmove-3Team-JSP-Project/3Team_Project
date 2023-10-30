package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// member 테이블에서 memberid 값이 id 파라메터와 동일한 데이터 조회
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) { // 같은 데이터가 존재하면 member객체 생성
				member = new Member(rs.getString("memberid"), rs.getString("name"), rs.getString("password"),
						toDate(rs.getTimestamp("regdate")));
				// JDBC 날짜,시간 타입은 Timestamp, member에서는 Date이기 때문에 Timestamp를 Date로 변환
			}
			return member; // 같은 데이터가 없으면 null 반환
		} finally {
			// TODO: handle finally clause
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

	// member 테이블에 데이터 추가
	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into member values (?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}

	// member 테이블 데이터 수정을 위한 메서드
	// member.getId()가 같은 레코드의 name, password 필드 값을 변경
	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update member set name = ?, password = ? where memberid = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		}
	}

}
