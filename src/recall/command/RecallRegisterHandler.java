package recall.command;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import recall.service.RecallRegisterService;
import recall.service.RecallRequest;

public class RecallRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/recall1/RecallRegisterForm.jsp";

	private RecallRegisterService registerService = new RecallRegisterService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		User user = (User) req.getSession().getAttribute("authUser");
		req.setAttribute("authUser", user);
		int recallNo = registerService.getRecallNo();
		req.setAttribute("recall_No", recallNo);

		Map<String, Integer> stockDetails1 = registerService.getStockNamesWithStockAmount();
		String stockDetails1Json = getStockDetails1Json(stockDetails1);
		req.setAttribute("stockDetails1Json", stockDetails1Json);

		Map<String, String> stockDetails2 = registerService.getStockNamesWithStorageNames();
		String stockDetails2Json = getStockDetails2Json(stockDetails2);
		req.setAttribute("stockDetails2Json", stockDetails2Json);

		List<String> stockNames = registerService.getStockName();
		req.setAttribute("stock_Name", stockNames);

		List<String> storageLists = registerService.getStorageList();
		req.setAttribute("storageLists", storageLists);

		return FORM_VIEW;

	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resq) throws SQLException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		RecallRequest recallRequest = createRecallRequest(req);
		recallRequest.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newRecallNo = registerService.register(recallRequest);

		req.setAttribute("newRecallNo", newRecallNo);
		return "/WEB-INF/view/recall1/recallRegisterSuccess.jsp";
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date process_Date = (Date) dateFormat.parse(d);
			return process_Date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private RecallRequest createRecallRequest(HttpServletRequest req) {
		return new RecallRequest(Integer.parseInt(req.getParameter("Recall_No")), req.getParameter("Member_Name"),
				req.getParameter("Storage_Name"), req.getParameter("Stock_Name"),
				Integer.parseInt(req.getParameter("Unit_Price")), Integer.parseInt(req.getParameter("Amount")),
				transformDate(req.getParameter("Process_Date")), req.getParameter("Process"));
	}

	public String getStockDetails1Json(Map<String, Integer> stockDetails1) {
		StringBuilder jsonBuilder = new StringBuilder("{");

		for (Map.Entry<String, Integer> entry : stockDetails1.entrySet()) {
			jsonBuilder.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
		}

		if (!stockDetails1.isEmpty()) {
			jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
		}
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}

	public String getStockDetails2Json(Map<String, String> stockDetails2) {
		StringBuilder jsonBuilder = new StringBuilder("{");
		for (Map.Entry<String, String> entry : stockDetails2.entrySet()) {
			jsonBuilder.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
		}

		if (!stockDetails2.isEmpty()) {
			jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
		}

		jsonBuilder.append("}");

		return jsonBuilder.toString();
	}
}