package order.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.service.OrderListService;
import order.service.OrderPage;

public class OrderListHandler implements CommandHandler {
	
	private OrderListService listService = new OrderListService();
	private static final String FORM_VIEW = "/WEB-INF/view/orderListForm.jsp";
	

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
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		OrderPage orderPage = listService.getOrderPage(pageNo); // 게시글 데이터 저장
		
		req.setAttribute("orderPage", orderPage); // JSP에서 사용하도록 속성 저장
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    String orderNoVal = req.getParameter("orderNo");
	    String progress = req.getParameter("progress");

	    if (orderNoVal == null || progress == null) {
	        // 파라미터가 유효하지 않을 경우 처리 (예: 오류 메시지 출력)
	        return FORM_VIEW;
	    }

	    int orderNo = Integer.parseInt(orderNoVal);

	    // DAO에 업데이트 요청 보내기
	    boolean updateSuccess = listService.updateOrderProgress(orderNo, progress);

	    if (updateSuccess) {
	        // 업데이트가 성공적으로 처리되었을 때의 처리
	        return FORM_VIEW; // 성공 화면으로 리다이렉트 또는 포워딩
	    } else {
	        // 업데이트에 실패했을 때의 처리
	        req.setAttribute("updateFailed", true);
	        return FORM_VIEW;
	    }
	}

}
