package item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import item.model.Item;
import item.service.ItemPage;
import jdbc.JdbcUtil;

public class ItemDao {

	// 데이터베이스에 상품 정보를 삽입하는 메서드
	public void insert(Connection conn, Item item) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO item (ITEM_ID, ITEM_NAME, UNIT_PRICE, ITEM_CLASS) VALUES (?, ?, ?, ?)")) {
			pstmt.setInt(1, item.getItem_Id());
			pstmt.setString(2, item.getItem_Name());
			pstmt.setInt(3, item.getUnit_Price());
			pstmt.setString(4, item.getItem_Class());
			int insertedCount = pstmt.executeUpdate();
			if (insertedCount <= 0) {
				throw new SQLException("Failed to insert Item");
			}
		}
	}

	// 페이징을 적용하여 상품 목록을 선택하는 메서드
	public List<Item> select(Connection conn, int startRow, int size) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM item ORDER BY item_name DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Item> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertItem(rs));
				}
				return result;
			}
		}
	}

	// 특정 클래스로 상품 목록을 선택하는 메서드
	public List<Item> selectByClass(Connection conn, String item_class, int startRow, int size) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM item WHERE item_class = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, item_class);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Item> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertItem(rs));
				}
				return result;
			}
		}
	}

	// 특정 상품 이름으로 상품 목록을 선택하는 메서드
	public List<Item> selectByName(Connection conn, String item_name, int startRow, int size) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM item WHERE item_name = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, item_name);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Item> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertItem(rs));
				}
				return result;
			}
		}
	}

	// 전체 상품 수를 반환하는 메서드
	public int getCount(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM item")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	// ResultSet에서 Item 객체로 변환하는 메서드
	private Item convertItem(ResultSet rs) throws SQLException {
		int item_id = rs.getInt("item_id");
		String item_name = rs.getString("item_name");
		int unit_price = rs.getInt("unit_price");
		String item_class = rs.getString("item_class");
		return new Item(item_id, item_name, unit_price, item_class);
	}

	// 특정 상품 이름에 해당하는 상품 수를 반환하는 메서드
	public int getCountByName(Connection conn, String item_name) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM item WHERE item_name = ?")) {
			pstmt.setString(1, item_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	// 특정 상품 클래스에 해당하는 상품 수를 반환하는 메서드
	public int getCountByClass(Connection conn, String item_class) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM item WHERE item_class = ?")) {
			pstmt.setString(1, item_class);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}
}
