package plan.dao;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import plan.model.Plan;

public class PlanDao {
	public Plan insert(Connection conn, Plan plan) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try { // article 테이블에 데이터 삽입
			pstmt = conn.prepareStatement("insert into plan values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, plan.getPlanNo());
			pstmt.setString(2, plan.getMemberName());
			pstmt.setString(3, plan.getStockName());
			pstmt.setInt(4, plan.getUnitPrice());
			pstmt.setInt(5, plan.getAmount());
			pstmt.setInt(6, plan.getPrice());
			pstmt.setString(7, plan.getCompanyName());
			pstmt.setString(8, plan.getStorageName());
			pstmt.setTimestamp(9, new Timestamp(plan.getPlanDate().getTime()));
			pstmt.setString(10, plan.getEnding());

			int insertedCount = pstmt.executeUpdate(); // executeUpdate : select 이외의 구문 수향, int 반환
			if (insertedCount > 0) { // insert, delete, update 구문에는 반영된 레코드 건수 반환, create, drop 구문은 -1 반환
				stmt = conn.createStatement(); // article 테이블에 새롭게 추가한 행의 article_no 값 구함
				rs = stmt
						.executeQuery("SELECT * FROM (SELECT plan_no FROM plan order BY ROWNUM DESC) WHERE ROWNUM = 1");
				if (rs.next()) { // 다음 게시글의 번호 구함 // executeQuery : select 구문 수행, ResultSet 반환
					Integer newNum = rs.getInt(1); // 컬럼의 숫자를 지정해서 값 호출
					return new Plan(newNum, plan.getMemberName(), plan.getStockName(), plan.getUnitPrice(),
							plan.getAmount(), plan.getPrice(), plan.getCompanyName(), plan.getStorageName(),
							plan.getPlanDate(), plan.getEnding());
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
			rs = stmt.executeQuery("select count(*) from plan");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public int selectCountEnding(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from plan WHERE Ending = '進行中'");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Plan> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 역순으로 정렬한 게시글 번호를 해당하는 레코드들을 읽어온다.
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from plan order by plan_no desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Plan> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertPlan(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Plan> selectEnding(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 역순으로 정렬한 게시글 번호를 해당하는 레코드들을 읽어온다.
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from plan WHERE Ending = '進行中' order by plan_no desc) a "
					+ "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Plan> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertPlan(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Plan> getSearch(Connection conn, String searchField, String searchText, int startRow, int size)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* "
				+ "FROM (SELECT * FROM Plan WHERE %s = ? ORDER BY Plan_NO DESC) a "
				+ "WHERE ROWNUM <= ?) WHERE rnum >= ?";
		String field = changeField(searchField);
		sql = String.format(sql, field);

		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("plan_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("stock_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("ending")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("plan_date")) {
				pstmt.setTimestamp(1, new Timestamp(transformDate(searchText).getTime()));
			}

			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);

			rs = pstmt.executeQuery();
			List<Plan> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertPlan(rs));
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
		String sql = "SELECT COUNT(*) FROM plan WHERE %s=?";
		String field = changeField(searchField);
		sql = String.format(sql, field);
		System.out.println(searchText);
		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("plan_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("stock_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("ending")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("plan_date")) {
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
		if (searchField.equals("planNo")) {
			field = "plan_no";
		} else if (searchField.equals("memberName")) {
			field = "member_name";
		} else if (searchField.equals("stockName")) {
			field = "stock_name";
		} else if (searchField.equals("companyName")) {
			field = "company_name";
		} else if (searchField.equals("storageName")) {
			field = "storage_name";
		} else if (searchField.equals("planDate")) {
			field = "plan_date";
		} else if (searchField.equals("ending")) {
			field = "ending";
		}
		return field;
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date planDate = dateFormat.parse(d);
			return planDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	private Plan convertPlan(ResultSet rs) throws SQLException {
		return new Plan(rs.getInt("plan_no"), rs.getString("member_name"), rs.getString("stock_name"),
				rs.getInt("unit_price"), rs.getInt("amount"), rs.getInt("price"), rs.getString("company_name"),
				rs.getString("storage_name"), rs.getTimestamp("plan_date"), rs.getString("ending"));
	}

	public int update(Connection conn, int planNo, String ending) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update plan set ending = ?" + " where plan_no = ?")) {
			pstmt.setString(1, ending);
			pstmt.setInt(2, planNo);
			return pstmt.executeUpdate();
		}
	}

	public int getPlanNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int planNo = 0;
		try {
			String getMaxplanNoQuery = "SELECT MAX(plan_no) FROM plan";
			pstmt = conn.prepareStatement(getMaxplanNoQuery);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				planNo = rs.getInt(1);
				if (rs.wasNull()) { // 만약 최대값이 NULL이면 기본값으로 3000001 사용
					planNo = 4000001;
				} else {
					planNo += 1; // 다음 주문번호 생성
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return planNo;
	}

	public List<String> getAllStockNames(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT stock_name FROM stock")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<String> stockNames = new ArrayList<>();
				while (rs.next()) {
					stockNames.add(rs.getString("stock_name"));
				}
				return stockNames;
			}
		}
	}

	public List<String> getCompanyList(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT Company_name FROM Company")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<String> CompanyNames = new ArrayList<>();
				while (rs.next()) {
					CompanyNames.add(rs.getString("company_name"));
				}
				return CompanyNames;
			}
		}
	}

	public List<String> getStorageList(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT Storage_name FROM Storage")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<String> storageNames = new ArrayList<>();
				while (rs.next()) {
					storageNames.add(rs.getString("Storage_name"));
				}
				return storageNames;
			}
		}
	}

	public Map<String, Integer> getStockNamesWithUnitPrice(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT stock_name, unit_price FROM stock")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				Map<String, Integer> stockDetails = new HashMap<>();
				while (rs.next()) {
					String stockName = rs.getString("stock_name");
					int unitPrice = rs.getInt("unit_price");
					stockDetails.put(stockName, unitPrice);
				}
				return stockDetails;
			}
		}
	}

	public int updateStockFromCompletedOrders(Connection conn, int planNo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("MERGE INTO STOCK st " + "USING ( " + "    SELECT "
				+ "        p.PLAN_NO, " + "        p.MEMBER_NAME, " + "        p.STOCK_NAME, "
				+ "        p.UNIT_PRICE, " + "        p.AMOUNT, " + "        p.PRICE, " + "        p.COMPANY_NAME, "
				+ "        p.STORAGE_NAME, " + "        p.PLAN_DATE, " + "        p.ENDING " + "    FROM PLAN p "
				+ "    WHERE p.PLAN_NO = ? " + "      AND p.ENDING = '完了' " + ") p "
				+ "ON (st.STORAGE_NAME = p.STORAGE_NAME AND st.STOCK_NAME = p.STOCK_NAME) " + "WHEN MATCHED THEN "
				+ "    UPDATE SET " + "        st.AMOUNT = st.AMOUNT - p.AMOUNT " + "WHEN NOT MATCHED THEN END;")) {

			pstmt.setInt(1, planNo);
			return pstmt.executeUpdate();
		}
	}
}
