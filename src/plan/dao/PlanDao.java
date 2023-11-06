package plan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import plan.model.Plan;

public class PlanDao {
	public Plan insert(Connection conn, Plan plan) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try { // article 테이블에 데이터 삽입
			pstmt = conn.prepareStatement("insert into plans values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, plan.getPlanNo());
			pstmt.setString(2, plan.getMemberName());
			pstmt.setString(3, plan.getStockName());
			pstmt.setInt(4, plan.getUnitPrice());
			pstmt.setInt(5, plan.getAmount());
			pstmt.setInt(6, plan.getPrice());
			pstmt.setString(7, plan.getCompanyName());
			pstmt.setString(8, plan.getStorageName());
			pstmt.setTimestamp(9, (Timestamp) plan.getPlanDate());
			pstmt.setString(10, plan.getEnding());

			int insertedCount = pstmt.executeUpdate(); // executeUpdate : select 이외의 구문 수향, int 반환
			if (insertedCount > 0) { // insert, delete, update 구문에는 반영된 레코드 건수 반환, create, drop 구문은 -1 반환
				stmt = conn.createStatement(); // article 테이블에 새롭게 추가한 행의 article_no 값 구함
				rs = stmt.executeQuery(
						"SELECT * FROM (SELECT plan_no FROM plan order BY ROWNUM DESC) WHERE ROWNUM = 1");
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

	private Plan convertPlan(ResultSet rs) throws SQLException {
		return new Plan(rs.getInt("plan_no"), rs.getString("member_name"), rs.getString("item_name"),
				rs.getInt("unit_price"), rs.getInt("amount"), rs.getInt("price"), rs.getString("company_name"),
				rs.getString("storage_name"), rs.getTimestamp("plan_date"), rs.getString("progress"));
	}

	public int update(Connection conn, int planNo, String progress) throws SQLException {
		try (PreparedStatement pstmt = conn
					.prepareStatement("update plan set progress = ?" + " where plan_no = ?")) {
				pstmt.setString(1, progress);
				pstmt.setInt(2, planNo);
				int result = pstmt.executeUpdate();

				if (result > 0) {
					dropTrigger(conn);

				}
				return result;
			}
	
	}

	public int updateSheetTrigger(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"CREATE OR REPLACE TRIGGER UPDATE_SHEET_PROCESS " + "AFTER UPDATE OF progress ON planS FOR EACH ROW "
						+ "BEGIN " + "UPDATE SHEET " + "SET process = :NEW.progress " + "WHERE list_no IN ("
						+ "SELECT list_no FROM sheet WHERE list_no = :NEW.plan_no" + "); " + "END;")) {

			return pstmt.executeUpdate();
		}

	}

	public void dropTrigger(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("DROP TRIGGER UPDATE_SHEET_PROCESS")) {
			pstmt.executeUpdate();
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
	                planNo = 3000001;
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

}
