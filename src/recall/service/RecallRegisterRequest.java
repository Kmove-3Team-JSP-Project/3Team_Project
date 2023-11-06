package recall.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recall.command.RecallRegisterHandler;

public class RecallRegisterRequest {
	private RecallRegisterHandler recallRegisterHandler;

	public RecallRegisterRequest(Connection connection) {
		this.recallRegisterHandler = new RecallRegisterHandler(connection);
	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String storageName = request.getParameter("storageName");
		String stockName = request.getParameter("stockName");
		int newAmount = Integer.parseInt(request.getParameter("newAmount"));

		try {
			recallRegisterHandler.modifyOrRemoveStockAmount(storageName, stockName, newAmount);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		response.sendRedirect("RecallRegisterSuccess.jsp");
	}
}
