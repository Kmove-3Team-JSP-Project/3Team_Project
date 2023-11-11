package order.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.service.OrderListService;
import order.service.OrderPage;

public class OrderSearchHandler implements CommandHandler {
	private OrderListService listService = new OrderListService();


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String condition =	req.getParameter("condition");
		String detail = req.getParameter("detail");
		
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		OrderPage orderPage = listService.getOrderSearchPage(condition, detail, pageNo); // 게시글 데이터 저장
		
		req.setAttribute("orderPage", orderPage);
		
		return "WEB-INF/view/order/orderSearchForm.jsp";
	}



	
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
