package recall.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recall.command.RecallRegisterHandler;

public class RecallRegisterRequest {
	private RecallRegisterHandler recallRegisterHandler;

	// 생성자: RecallRegisterHandler를 초기화하는 역할
	public RecallRegisterRequest(Connection connection) {
		this.recallRegisterHandler = new RecallRegisterHandler(connection);
	}

	// 요청 처리 메서드
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// HTTP 요청에서 storageName, stockName, newAmount 파라미터를 읽어옴
		String storageName = request.getParameter("storageName");
		String stockName = request.getParameter("stockName");
		int newAmount = Integer.parseInt(request.getParameter("newAmount"));

		try {
			// RecallRegisterHandler를 사용하여 stock의 수량을 수정 또는 삭제하는 메서드를 호출
			recallRegisterHandler.modifyOrRemoveStockAmount(storageName, stockName, newAmount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 처리 결과를 RecallRegisterSuccess.jsp 페이지로 리다이렉트
		response.sendRedirect("RecallRegisterSuccess.jsp");
	}
}