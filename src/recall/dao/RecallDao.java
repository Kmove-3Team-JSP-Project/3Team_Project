package recall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import recall.model.Recall;

public class RecallDao {
	public Recall insert(Connection conn, Recall recall) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("insert into recall values (?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, recall.getRecall_No());
			pstmt.setString(2, recall.getMember_Name());
			pstmt.setString(3, recall.getStorage_Name());
			pstmt.setString(4, recall.getStock_Name());
			pstmt.setInt(5, recall.getUnit_Price());
			pstmt.setInt(6, recall.getAmount());
			pstmt.setTimestamp(7, new Timestamp(recall.getProcess_Date().getTime()));
			pstmt.setString(8, recall.getProcess());

			int insertedCount = pstmt.executeUpdate();
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
						"SELECT * FROM(SELET recall_no FROM ORDERS recall BY ROWNUM DESC) WHERE ROWNUM = 1");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Recall(newNum, recall.getMember_Name(), recall.getStorage_Name(), recall.getStock_Name(),
							recall.getUnit_Price(), recall.getAmount(), recall.getProcess_Date(), recall.getProcess());
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
			rs = stmt.executeQuery("select count(*)from recall");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Recall> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, a.* "
					+ "from (select * from Recall order by Recall_No desc) a " + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Recall> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertRecall(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Recall convertRecall(ResultSet rs) throws SQLException {
		return new Recall(rs.getInt("recall_no"), rs.getString("member_name"), rs.getString("storage_name"),
				rs.getString("stock_name"), rs.getInt("unit_price"), rs.getInt("amount"), rs.getDate("process_date"),
				rs.getString("process"));
	}

	public int update(Connection conn, int recallNo, String process) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update recall set process = ?" + " where recall_no = ?")) {
			pstmt.setString(1, process);
			pstmt.setInt(2, recallNo);
			return pstmt.executeUpdate();
		}
	}

	public int getRecallNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int recallNo = 0;
		try {
			String getMaxRecallNoQuery = "SELECT MAX(recall_no) FROM recall";
			pstmt = conn.prepareStatement(getMaxRecallNoQuery);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				recallNo = rs.getInt(1);
				if (rs.wasNull()) {
					recallNo = 9000001;
				} else {
					recallNo += 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return recallNo;
	}

	public List<String> getStockName(Connection conn) throws SQLException {
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

	public Map<String, Integer> getStockNamesWithStockAmount(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT stock_name, amount FROM stock")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				Map<String, Integer> stockDetails1 = new HashMap<>();
				while (rs.next()) {
					String stockName = rs.getString("stock_name");
					int amount = rs.getInt("amount");
					stockDetails1.put(stockName, amount);
				}
				return stockDetails1;
			}
		}
	}

	public Map<String, String> getStockNamesWithStorageNames(Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT stock_name, storage_name FROM stock")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				Map<String, String> stockDetails2 = new HashMap<>();
				while (rs.next()) {
					String stockName = rs.getString("stock_name");
					String storageName = rs.getString("storage_name");
					stockDetails2.put(stockName, storageName);
				}
				return stockDetails2;
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
}