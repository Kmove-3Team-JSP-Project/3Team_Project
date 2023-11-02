package plan.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp.service.OrderListService;
import erp.service.OrderPage;
import mvc.command.CommandHandler;

public class PlanSearchHandler implements CommandHandler {
	private OrderListService listService = new OrderListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		OrderPage orderPage = listService.getOrderPage(pageNo); // 게시글 데이터 저장
		req.setAttribute("orderPage", orderPage); // JSP에서 사용하도록 속성 저장
		return "/WEB-INF/view/orderListForm.jsp";
	}

}
