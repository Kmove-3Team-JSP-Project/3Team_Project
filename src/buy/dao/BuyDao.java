package buy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import buy.model.Buy;
import jdbc.JdbcUtil;

public class BuyDao {
	public Buy insert(Connection conn, Buy buy) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// SQL 쿼리를 사용하여 주차권 정보를 데이터베이스에 삽입
			pstmt = conn.prepareStatement(
					"insert into Buy " + " (date_No, unit_price, company_name, price, amount, buy_date, member_Name)"
							+ "values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, buy.getDate_No()); //
			pstmt.setInt(2, buy.getUnit_Price());
			pstmt.setString(3, buy.getCompany_Name());
			pstmt.setInt(4, buy.getPrice());
			pstmt.setInt(5, buy.getAmount());
			pstmt.setInt(6, buy.getBuy_date());
			pstmt.setString(7, buy.getMember_Name());
			return null;

		} finally {
			// 사용된 자원을 닫음
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Buy> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from company order by rownum desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size); // 첫 번째 바인딩 변수 설정
			pstmt.setInt(2, startRow + 1); // 두 번째 바인딩 변수 설정

			// SQL 쿼리 실행
			rs = pstmt.executeQuery();
			List<Buy> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertBuy(rs)); // ResultSet의 결과를 Ticket 객체로 변환하고 목록에 추가
			}
			return result; // 페이징된 주차권 목록을 반환
		} finally {
			JdbcUtil.close(rs); // ResultSet 자원 닫기
			JdbcUtil.close(pstmt); // PreparedStatement 자원 닫기
		}
	}

	private Buy convertBuy(ResultSet rs) throws SQLException {
		return new Buy(rs.getInt("date_No"), rs.getInt("unit_Price"), rs.getString("company_Name"),
				rs.getString("item_Name"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("buy_date"),
				rs.getString("member_Name"));
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from buy");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}

	}
}
