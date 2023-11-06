package recall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recall.model.Recall;
import stock.model.Stock;

public class RecallDao {
	public void insert(Connection conn, Recall recall) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("INSERT INTO recall (Recall_No, MEMBER_NAME, STORAGE_NAME, STOCK_NAME, Unit_Price,"
						+ "AMOUNT, PROCESS_DATE, PROCESS) VALUES (?,?,?,?,?,?,?,?)")) {
			pstmt.setInt(1, recall.getRecall_No());
			pstmt.setString(2, recall.getMember_Name());
			pstmt.setString(3, recall.getStorage_Name());
			pstmt.setString(4, recall.getStock_Name());
			pstmt.setInt(5, recall.getUnit_Price());
			pstmt.setInt(6, recall.getAmount());
			pstmt.setTimestamp(7, toTimestamp(recall.getProcess_Date()));
			pstmt.setString(8, recall.getProcess());
			int insertedCount = pstmt.executeUpdate();
			if (insertedCount <= 0) {
				throw new SQLException("Failed to insert Recall");
			}
		}
	}

	public List<Recall> select(Connection conn, int startRow, int size) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM recall ORDER BY stock_name DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Recall> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertRecall(rs));
				}
				return result;
			}
		}
	}

	private Recall convertRecall(ResultSet rs) throws SQLException {
		int recall_no = rs.getInt("recall_no");
		String member_name = rs.getString("member_name");
		String storage_name = rs.getString("storage_name");
		String stock_name = rs.getString("stock_name");
		int unit_price = rs.getInt("unit_price");
		int amount = rs.getInt("amount");
		Date process_date = rs.getDate("process_date");
		String process = rs.getString("process");
		return new Recall(recall_no, member_name, storage_name, stock_name, unit_price, amount, process_date, process);
	}

	public List<Stock> selectByStock(Connection conn, String stock_name, int startRow, int size) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM stock WHERE stock_name = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, stock_name);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Stock> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertStock(rs));
				}
				return result;
			}
		}
	}

	public List<Stock> selectByStorage(Connection conn, String storage_name, int startRow, int size)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM stock WHERE storage_name = ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?")) {
			pstmt.setString(1, storage_name);
			pstmt.setInt(2, startRow + size);
			pstmt.setInt(3, startRow + 1);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Stock> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertStock(rs));
				}
				return result;
			}
		}
	}

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

	public int getCount(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM stock")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	private Stock convertStock(ResultSet rs) throws SQLException {
		int stock_cord = rs.getInt("stock_cord");
		int stock_amount = rs.getInt("amount");
		String storage_name = rs.getString("storage_name");
		String stock_name = rs.getString("stock_name");
		int unit_price = rs.getInt("unit_price");
		return new Stock(stock_cord, stock_amount, storage_name, stock_name, unit_price);
	}

	public int getCountByStock(Connection conn, String stock_name) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM stock WHERE stock_name = ?")) {
			pstmt.setString(1, stock_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	public int getCountByStorage(Connection conn, String storage_name) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM storage WHERE storage_name = ?")) {
			pstmt.setString(1, storage_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

}
