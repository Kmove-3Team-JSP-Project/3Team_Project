package sheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import sheet.model.Sheet;

public class SheetDao {
	public Sheet insert(Connection conn, Sheet sheet) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try { // article 테이블에 데이터 삽입
			pstmt = conn.prepareStatement("insert into sheet values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, sheet.getListNo());
			pstmt.setString(2, sheet.getMemberName());
			pstmt.setString(3, sheet.getProductName());
			pstmt.setInt(4, sheet.getUnitPrice());
			pstmt.setInt(5, sheet.getAmount());
			pstmt.setInt(6, sheet.getPrice());
			pstmt.setString(7, sheet.getCompanyName());
			pstmt.setString(8, sheet.getStorageName());
			pstmt.setTimestamp(9, (Timestamp) sheet.getListDate());
			pstmt.setString(10, sheet.getProcess());

			int insertedCount = pstmt.executeUpdate(); // executeUpdate : select 이외의 구문 수향, int 반환
			if (insertedCount > 0) { // insert, delete, update 구문에는 반영된 레코드 건수 반환, create, drop 구문은 -1 반환
				stmt = conn.createStatement(); // article 테이블에 새롭게 추가한 행의 article_no 값 구함
				rs = stmt.executeQuery(
						"SELECT * FROM (SELECT list_no FROM sheet order BY ROWNUM DESC) WHERE ROWNUM = 1");
				if (rs.next()) { // 다음 게시글의 번호 구함 // executeQuery : select 구문 수행, ResultSet 반환
					Integer newNum = rs.getInt(1); // 컬럼의 숫자를 지정해서 값 호출
					return new Sheet(newNum, sheet.getMemberName(), sheet.getProductName(), sheet.getUnitPrice(),
							sheet.getAmount(), sheet.getPrice(), sheet.getCompanyName(), sheet.getStorageName(),
							sheet.getListDate(), sheet.getProcess());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}

	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from sheet");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Sheet> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 역순으로 정렬한 게시글 번호를 해당하는 레코드들을 읽어온다.
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from sheet order by list_no desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Sheet> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertSheet(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Sheet convertSheet(ResultSet rs) throws SQLException {
		return new Sheet(rs.getInt("list_no"), rs.getString("member_name"), rs.getString("Product_name"),
				rs.getInt("unit_price"), rs.getInt("amount"), rs.getInt("price"), rs.getString("company_name"),
				rs.getString("storage_name"), rs.getTimestamp("list_date"), rs.getString("process"));
	}


}
