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
	public Item insert(Connection conn, Item item) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into item (ITEM_ID, ITEM_NAME, UNIT_PRICE, ITEM_CLASS) values (?, ?, ?, ?)");
			pstmt.setInt(1, item.getItem_Id());
			pstmt.setString(2, item.getItem_Name());
			pstmt.setInt(3, item.getUnit_Price());
			pstmt.setString(4, item.getItem_Class());
			int insertedCount = pstmt.executeUpdate();
			if (insertedCount > 0) {
				return item;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Item> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM item ORDER BY item_name DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Item> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertItem(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Item convertItem(ResultSet rs) throws SQLException {
		return new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getInt("unit_price"),
				rs.getString("item_class"));
	}

	public int getCount(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM item")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
		}
	}

	public Item selectByName(Connection conn, String item_name) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from item where item_name = ?");
			pstmt.setString(1, item_name);
			rs = pstmt.executeQuery();
			Item item = null;
			if (rs.next()) {
				item = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getInt("unit_price"),
						rs.getString("item_class"));
			}
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Item selectByClass(Connection conn, String item_class) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from item where item_class = ?");
			pstmt.setString(1, item_class);
			rs = pstmt.executeQuery();
			Item item = null;
			if (rs.next()) {
				item = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getInt("unit_price"),
						rs.getString("item_class"));
			}
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
