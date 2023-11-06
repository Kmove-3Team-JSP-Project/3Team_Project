package order.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.service.OrderListService;

public class OrderSearchHandler implements CommandHandler {
	private OrderListService listService = new OrderListService();
	private static final String FORM_VIEW = "WEB-INF/view/orderSearchForm.jsp";

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
		/*
		 * Map<String, Boolean> errors = new HashMap<>(); req.setAttribute("errors",
		 * errors);
		 * 
		 * User user = (User) req.getSession(false).getAttribute("authUser");
		 * OrderRequest orderReq = createOrderRequest(user, req);
		 * orderReq.validate(errors);
		 * 
		 * if (!errors.isEmpty()) { return FORM_VIEW; }
		 */

		return "/WEB-INF/view/orderListForm.jsp";
	}

	/*
	 * public Date transformDate(String d) { SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * try { Date orderDate = (Date) dateFormat.parse(d); return orderDate; } catch
	 * (ParseException e) { e.printStackTrace(); return null; }
	 * 
	 * }
	 */
}
