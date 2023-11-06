package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import order.model.Order;

public class OrderDao {

	public Order insert(Connection conn, Order order) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try { // article 테이블에 데이터 삽입
			pstmt = conn.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, order.getOrderNo());
			pstmt.setString(2, order.getMemberName());
			pstmt.setString(3, order.getItemName());
			pstmt.setInt(4, order.getUnitPrice());
			pstmt.setInt(5, order.getAmount());
			pstmt.setInt(6, order.getPrice());
			pstmt.setString(7, order.getCompanyName());
			pstmt.setString(8, order.getStorageName());
			pstmt.setTimestamp(9, (Timestamp) order.getOrderDate());
			pstmt.setString(10, order.getProgress());

			int insertedCount = pstmt.executeUpdate(); // executeUpdate : select 이외의 구문 수향, int 반환
			if (insertedCount > 0) { // insert, delete, update 구문에는 반영된 레코드 건수 반환, create, drop 구문은 -1 반환
				stmt = conn.createStatement(); // article 테이블에 새롭게 추가한 행의 article_no 값 구함
				rs = stmt.executeQuery(
						"SELECT * FROM (SELECT order_no FROM ORDERS order BY ROWNUM DESC) WHERE ROWNUM = 1");
				if (rs.next()) { // 다음 게시글의 번호 구함 // executeQuery : select 구문 수행, ResultSet 반환
					Integer newNum = rs.getInt(1); // 컬럼의 숫자를 지정해서 값 호출
					return new Order(newNum, order.getMemberName(), order.getItemName(), order.getUnitPrice(),
							order.getAmount(), order.getPrice(), order.getCompanyName(), order.getStorageName(),
							order.getOrderDate(), order.getProgress());
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
			rs = stmt.executeQuery("select count(*) from orderS");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Order> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 역순으로 정렬한 게시글 번호를 해당하는 레코드들을 읽어온다.
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from ORDERS order by ORDER_no desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Order> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertOrder(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Order convertOrder(ResultSet rs) throws SQLException {
		return new Order(rs.getInt("order_no"), rs.getString("member_name"), rs.getString("item_name"),
				rs.getInt("unit_price"), rs.getInt("amount"), rs.getInt("price"), rs.getString("company_name"),
				rs.getString("storage_name"), rs.getTimestamp("order_date"), rs.getString("progress"));
	}

	public int update(Connection conn, int orderNo, String progress) throws SQLException {
		try (PreparedStatement pstmt = conn
					.prepareStatement("update Orders set progress = ?" + " where order_no = ?")) {
				pstmt.setString(1, progress);
				pstmt.setInt(2, orderNo);
				int result = pstmt.executeUpdate();

				if (result > 0) {
					dropTrigger(conn);

				}
				return result;
			}
	
	}

	public int updateSheetTrigger(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"CREATE OR REPLACE TRIGGER UPDATE_SHEET_PROCESS " + "AFTER UPDATE OF progress ON ORDERS FOR EACH ROW "
						+ "BEGIN " + "UPDATE SHEET " + "SET process = :NEW.progress " + "WHERE list_no IN ("
						+ "SELECT list_no FROM sheet WHERE list_no = :NEW.order_no" + "); " + "END;")) {

			return pstmt.executeUpdate();
		}

	}

	public void dropTrigger(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("DROP TRIGGER UPDATE_SHEET_PROCESS")) {
			pstmt.executeUpdate();
		}
	}

	public int getOrderNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int orderNo = 0;
		try {
			String getMaxOrderNoQuery = "SELECT MAX(order_no) FROM orders";
			pstmt = conn.prepareStatement(getMaxOrderNoQuery);
			rs = pstmt.executeQuery();

			if (rs.next()) {
	            orderNo = rs.getInt(1);
	            if (rs.wasNull()) { // 만약 최대값이 NULL이면 기본값으로 3000001 사용
	                orderNo = 3000001;
	            } else {
	                orderNo += 1; // 다음 주문번호 생성
	            }
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return orderNo;
	}

}
