package order.dao;

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
import order.model.Order;

public class OrderDao {

	public Order insert(Connection conn, Order order) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, order.getOrderNo());
			pstmt.setString(2, order.getMemberName());
			pstmt.setString(3, order.getItemName());
			pstmt.setInt(4, order.getUnitPrice());
			pstmt.setInt(5, order.getAmount());
			pstmt.setInt(6, order.getPrice());
			pstmt.setString(7, order.getCompanyName());
			pstmt.setString(8, order.getStorageName());
			pstmt.setTimestamp(9, new Timestamp(order.getOrderDate().getTime()));
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
			rs = stmt.executeQuery("select count(*) from orders");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public int selectCountProgress(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from orders WHERE PROGRESS = '進行中'");
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

	public List<Order> selectProgress(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // 역순으로 정렬한 게시글 번호를 해당하는 레코드들을 읽어온다.
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from ORDERS WHERE PROGRESS = '進行中' order by ORDER_no desc) a "
					+ "where rownum <=?) where rnum >=?");
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

	public List<Order> getSearch(Connection conn, String searchField, String searchText, int startRow, int size)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* "
				+ "FROM (SELECT * FROM ORDERS WHERE %s = ? ORDER BY ORDER_NO DESC) a "
				+ "WHERE ROWNUM <= ?) WHERE rnum >= ?";
		String field = changeField(searchField);
		sql = String.format(sql, field);

		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("order_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("item_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("progress")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("order_date")) {
				pstmt.setTimestamp(1, new Timestamp(transformDate(searchText).getTime()));
			}

			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);

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

	public int selectCountSearch(Connection conn, String searchField, String searchText) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM orders WHERE %s=?";
		String field = changeField(searchField);
		sql = String.format(sql, field);
		try {
			pstmt = conn.prepareStatement(sql);

			if (field.equals("order_no")) {
				pstmt.setInt(1, Integer.parseInt(searchText));
			} else if (field.equals("member_name") || field.equals("item_name") || field.equals("company_name")
					|| field.equals("storage_name") || field.equals("progress")) {
				pstmt.setString(1, searchText);
			} else if (field.equals("order_date")) {
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
		if (searchField.equals("orderNo")) {
			field = "order_no";
		} else if (searchField.equals("memberName")) {
			field = "member_name";
		} else if (searchField.equals("itemName")) {
			field = "item_name";
		} else if (searchField.equals("companyName")) {
			field = "company_name";
		} else if (searchField.equals("storageName")) {
			field = "storage_name";
		} else if (searchField.equals("orderDate")) {
			field = "order_date";
		} else if (searchField.equals("progress")) {
			field = "progress";
		}
		return field;
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date orderDate = dateFormat.parse(d);
			return orderDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
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
			return pstmt.executeUpdate();
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

	public List<String> getAllItemNames(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT item_name FROM item")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				List<String> itemNames = new ArrayList<>();
				while (rs.next()) {
					itemNames.add(rs.getString("item_name"));
				}
				return itemNames;
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

	public Map<String, Integer> getItemNamesWithUnitPrice(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT item_name, unit_price FROM item")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				Map<String, Integer> itemDetails = new HashMap<>();
				while (rs.next()) {
					String itemName = rs.getString("item_name");
					int unitPrice = rs.getInt("unit_price");
					itemDetails.put(itemName, unitPrice);
				}
				return itemDetails;
			}
		}
	}

	public int updateStockFromCompletedOrders(Connection conn, int orderNo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("MERGE INTO STOCK st " + "USING ( " + "    SELECT "
				+ "        o.ORDER_NO, " + "        o.MEMBER_NAME, " + "        o.ITEM_NAME, "
				+ "        o.UNIT_PRICE, " + "        o.AMOUNT, " + "        o.PRICE, " + "        o.COMPANY_NAME, "
				+ "        o.STORAGE_NAME, " + "        o.ORDER_DATE, " + "        o.PROGRESS " + "    FROM ORDERS o "
				+ "    WHERE o.ORDER_NO = ? " + "      AND o.PROGRESS = '完了' " + ") o "
				+ "ON (st.STORAGE_NAME = o.STORAGE_NAME AND st.STOCK_NAME = o.ITEM_NAME) " + "WHEN MATCHED THEN "
				+ "    UPDATE SET " + "        st.AMOUNT = st.AMOUNT + o.AMOUNT " + "WHEN NOT MATCHED THEN "
				+ "    INSERT (STOCK_CORD, AMOUNT, STORAGE_NAME, STOCK_NAME, UNIT_PRICE) "
				+ "    VALUES (stock_seq.NEXTVAL, o.AMOUNT, o.STORAGE_NAME, o.ITEM_NAME, o.UNIT_PRICE)")) {

			pstmt.setInt(1, orderNo);
			return pstmt.executeUpdate();
		}
	}

}
