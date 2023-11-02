package order.command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import erp.service.OrderRegisterService;
import erp.service.OrderRequest;
import mvc.command.CommandHandler;

public class OrderRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "WEB-INF/view/orderRegisterForm.jsp";
	private OrderRegisterService regiService = new OrderRegisterService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User) req.getSession(false).getAttribute("authUser");
		OrderRequest orderReq = createOrderRequest(user, req);
		orderReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newOrderNo = regiService.register(orderReq);
		req.setAttribute("newOrderNo", newOrderNo);

		return "/WEB-INF/view/orderFormView.jsp";
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date orderDate = (Date)dateFormat.parse(d);
			return orderDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	private OrderRequest createOrderRequest(User user, HttpServletRequest req) {
		return new OrderRequest(Integer.parseInt(req.getParameter("orderNo")), user.getName(), req.getParameter("itemName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("orderDate")), req.getParameter("progress"));
	}
}