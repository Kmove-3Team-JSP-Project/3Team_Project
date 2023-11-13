package sheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			pstmt.setTimestamp(9, new Timestamp(sheet.getListDate().getTime()));
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
	public List<Sheet> getSearch(Connection conn, String searchField, String searchText, int startRow, int size)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* "
				+ "FROM (SELECT * FROM Sheet WHERE %s = ? ORDER BY list_NO DESC) a "
				+ "WHERE ROWNUM <= ?) WHERE rnum >= ?";
		String field = changeField(searchField);
		sql = String.format(sql, field);

		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("list_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("product_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("process")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("list_date")) {
				pstmt.setTimestamp(1, new Timestamp(transformDate(searchText).getTime()));
			}

			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);

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
	
	public int selectCountSearch(Connection conn, String searchField, String searchText) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM sheet WHERE %s=?";
		String field = changeField(searchField);
		sql = String.format(sql, field);
		System.out.println(searchText);
		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("list_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("product_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("process")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("list_date")) {
				Date date = transformDate(searchText);
				pstmt.setTimestamp(1, new Timestamp(date.getTime()));
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private String changeField(String searchField) {
		String field = "";
		if (searchField.equals("listNo")) {
			field = "list_no";
		} else if (searchField.equals("memberName")) {
			field = "member_name";
		} else if (searchField.equals("productName")) {
			field = "product_name";
		} else if (searchField.equals("companyName")) {
			field = "company_name";
		} else if (searchField.equals("storageName")) {
			field = "storage_name";
		} else if (searchField.equals("listDate")) {
			field = "list_date";
		} else if (searchField.equals("process")) {
			field = "process";
		}
		return field;
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date listDate = dateFormat.parse(d);
			return listDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	private Sheet convertSheet(ResultSet rs) throws SQLException {
		return new Sheet(rs.getInt("list_no"), rs.getString("member_name"), rs.getString("product_name"),
				rs.getInt("unit_price"), rs.getInt("amount"), rs.getInt("price"), rs.getString("company_name"),
				rs.getString("storage_name"), rs.getTimestamp("list_date"), rs.getString("process"));
	}

	public int update(Connection conn, int listNo, String process) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update sheet set process = ?" + " where list_no = ?")) {
				pstmt.setString(1, process);
				pstmt.setInt(2, listNo);
				return pstmt.executeUpdate();
			}
	
	}

}
