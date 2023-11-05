package recall.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecallRegisterHandler {
	private Connection connection;

	public RecallRegisterHandler(Connection connection) {
		this.connection = connection;
	}

	public void modifyOrRemoveStockAmount(String storageName, String stockName, int newAmount) throws SQLException {
		int currentAmount = getCurrentAmount(storageName, stockName);

		if (newAmount == currentAmount) {
			removeStockAmount(storageName, stockName);
		} else if (newAmount > currentAmount) {
			modifyStockAmount(storageName, stockName, newAmount);
		} else {
			modifyStockAmount(storageName, stockName, newAmount);
		}
	}

	private int getCurrentAmount(String storageName, String stockName) throws SQLException {
		String sql = "SELECT AMOUNT FROM STOCK WHERE STORAGE_NAME = ? AND STOCK_NAME = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, storageName);
			pstmt.setString(2, stockName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("AMOUNT");
				}
			}
		}
		return 0;
	}

	private void modifyStockAmount(String storageName, String stockName, int newAmount) throws SQLException {
		String sql = "UPDATE STOCK SET AMOUNT = ? WHERE STORAGE_NAME = ? AND STOCK_NAME = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, newAmount);
			pstmt.setString(2, storageName);
			pstmt.setString(3, stockName);
			pstmt.executeUpdate();
		}
	}

	private void removeStockAmount(String storageName, String stockName) throws SQLException {
		String sql = "DELETE FROM STOCK WHERE STORAGE_NAME = ? AND STOCK_NAME = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, storageName);
			pstmt.setString(2, stockName);
			pstmt.executeUpdate();
		}
	}
}
