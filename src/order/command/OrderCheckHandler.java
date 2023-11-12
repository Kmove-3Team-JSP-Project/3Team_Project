package order.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.service.OrderListService;
import order.service.OrderPage;
import order.service.OrderUpdateService;

public class OrderCheckHandler implements CommandHandler {

	private OrderListService listService = new OrderListService();
	private OrderUpdateService UpdateService = new OrderUpdateService();

	private static final String FORM_VIEW = "/WEB-INF/view/order/orderCheckForm.jsp";

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
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		OrderPage orderPage = listService.getOrderCheckPage(pageNo); // 게시글 데이터 저장

		req.setAttribute("orderPage", orderPage); // JSP에서 사용하도록 속성 저장
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    String jsonData = req.getParameter("orderNoArray");
	    System.out.println(jsonData);
	    if (jsonData == null || jsonData.isEmpty()) {
	        // 유효하지 않은 데이터
	        return FORM_VIEW;
	    }

	    // JSON 문자열 파싱
	    String[] keyValuePairs = jsonData.substring(2, jsonData.length() - 2).split(",");
	    int updateSuccess = 0;

	    // 주문 번호 리스트
	    List<Integer> orderNosToUpdate = new ArrayList<>();

	    for (String pair : keyValuePairs) {
	        String[] keyValue = pair.split(":");
	        String key = keyValue[0].trim().replaceAll("\"", "");
	        String value = keyValue[1].trim().replaceAll("\"", "");

	        if ("orderNo".equals(key)) {
	            // "orderNo" 처리
	            int orderNoToUpdate = Integer.parseInt(value);
	            System.out.println(orderNoToUpdate);
	            orderNosToUpdate.add(orderNoToUpdate); // 주문 번호를 리스트에 추가
	        } else if ("progress".equals(key)) {
	            // "progress" 처리
	            String progress = value;

	            // 주문 상태 업데이트
	            for (int orderNo : orderNosToUpdate) {
	                int orderUpdateResult = UpdateService.updateOrderProgress(orderNo, progress);

	                // 시트 상태 업데이트
	                int sheetUpdateResult = UpdateService.updateSheetProgress(orderNo, progress);

	                req.setAttribute("orderUpdateResult", orderUpdateResult);
	                req.setAttribute("sheetUpdateResult", sheetUpdateResult);

	                updateSuccess++; // 업데이트 성공 횟수 증가
	            }
	        }
	    }

	    if (updateSuccess > 0) {
	        // 업데이트가 성공적으로 처리되었을 때의 처리
	        return "/WEB-INF/view/order/orderCheckSuccess.jsp"; // 성공 화면으로 리다이렉트 또는 포워딩
	    } else {
	        // 업데이트에 실패했을 때의 처리
	        req.setAttribute("updateFailed", true);
	        return FORM_VIEW;
	    }
	}


}
