package recall.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class RecallRegisterHandler implements CommandHandler {
	private Connection connection;

	// RecallRegisterHandler 클래스의 생성자로, Connection을 받아와 필드에 저장하는 역할을 합니다.
	public RecallRegisterHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 웹 요청에서 필요한 정보를 가져옵니다.
		String storageName = req.getParameter("storageName");
		String stockName = req.getParameter("stockName");
		int newAmount = Integer.parseInt(req.getParameter("newAmount"));

		try {
			// Stock의 수량을 변경하거나 제거하는 메서드를 호출합니다.
			modifyOrRemoveStockAmount(storageName, stockName, newAmount);
			return "/WEB-INF/view/recall/RecallRegisterSuccess.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Stock의 수량을 변경하거나 제거하는 메서드
	public void modifyOrRemoveStockAmount(String storageName, String stockName, int newAmount) throws SQLException {
		int currentAmount = getCurrentAmount(storageName, stockName);

		if (newAmount == currentAmount) {
			// 수량이 동일하면 Stock 레코드를 제거합니다.
			removeStockAmount(storageName, stockName);
		} else if (newAmount > currentAmount) {
			// 수량이 증가하면 Stock 레코드를 수정합니다.
			modifyStockAmount(storageName, stockName, newAmount);
		} else {
			// 수량이 감소하면 Stock 레코드를 수정합니다.
			modifyStockAmount(storageName, stockName, newAmount);
		}
	}

	// 주어진 저장소 이름과 물품 이름에 해당하는 Stock 레코드의 현재 수량을 가져오는 메서드
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

	// 주어진 저장소 이름과 물품 이름에 해당하는 Stock 레코드의 수량을 수정하는 메서드
	private void modifyStockAmount(String storageName, String stockName, int newAmount) throws SQLException {
		String sql = "UPDATE STOCK SET AMOUNT = ? WHERE STORAGE_NAME = ? AND STOCK_NAME = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, newAmount);
			pstmt.setString(2, storageName);
			pstmt.setString(3, stockName);
			pstmt.executeUpdate();
		}
	}

	// 주어진 저장소 이름과 물품 이름에 해당하는 Stock 레코드를 제거하는 메서드
	private void removeStockAmount(String storageName, String stockName) throws SQLException {
		String sql = "DELETE FROM STOCK WHERE STORAGE_NAME = ? AND STOCK_NAME = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, storageName);
			pstmt.setString(2, stockName);
			pstmt.executeUpdate();
		}
	}
}
