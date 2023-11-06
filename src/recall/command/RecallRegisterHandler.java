package recall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class RecallRegisterHandler implements CommandHandler {
	private Connection connection;

	public RecallRegisterHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String storageName = req.getParameter("storageName");
		String stockName = req.getParameter("stockName");
		int newAmount = Integer.parseInt(req.getParameter("newAmount"));

		try {
			modifyOrRemoveStockAmount(storageName, stockName, newAmount);
			return "/WEB-INF/view/recall/RecallRegisterSuccess.jsp";
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
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
