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
		String detail = req.getParameter("detail").trim();
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		
		OrderPage orderPage = listService.getOrderSearchPage(condition, detail, pageNo); // 게시글 데이터 저장	
		req.setAttribute("orderPage", orderPage);
		req.setAttribute("condition", condition);
		req.setAttribute("detail", detail);
		return "/WEB-INF/view/order/orderSearchForm.jsp";
	}
}
