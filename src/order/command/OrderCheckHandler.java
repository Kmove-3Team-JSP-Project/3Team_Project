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

		// 대괄호를 제거하고 각 항목으로 분할
		String[] entries = jsonData.substring(1, jsonData.length() - 1).split("\\},");

		// 주문 번호 리스트
		List<Integer> orderNosToUpdate = new ArrayList<>();
		int updateSuccess = 0;

		// 각 항목 처리
		for (String entry : entries) {
			// orderNo 및 progress 추출
			int orderNo = extractValue(entry, "orderNo");
			String progress = extractValueString(entry, "progress");

			// 주문 번호 리스트에 추가
			orderNosToUpdate.add(orderNo);

			// 주문 상태 업데이트
			for (int orderNoToUpdate : orderNosToUpdate) {
				// 시트 상태 업데이트
				int sheetUpdateResult = UpdateService.updateSheetProgress(orderNoToUpdate, progress);

				// 주문 상태 업데이트
				int orderUpdateResult = UpdateService.updateOrderProgress(orderNoToUpdate, progress);

				// 각 주문 번호에 대한 결과를 개별적으로 저장
				req.setAttribute("orderUpdateResult_" + orderNoToUpdate, orderUpdateResult);
				req.setAttribute("sheetUpdateResult_" + orderNoToUpdate, sheetUpdateResult);

				updateSuccess++; // 업데이트 성공 횟수 증가
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

	private static int extractValue(String entry, String key) {
	    int keyIndex = entry.indexOf("\"" + key + "\":") + key.length() + 3;
	    int endIndex = entry.indexOf(",", keyIndex);
	    if (endIndex == -1) {
	        endIndex = entry.indexOf("}", keyIndex);
	        if (endIndex == -1) {
	            endIndex = entry.length(); // , 또는 }가 없을 경우 문자열의 끝까지 사용
	        }
	    }
	    String value = entry.substring(keyIndex, endIndex);
	    return Integer.parseInt(value);
	}

	private static String extractValueString(String entry, String key) {
	    int keyIndex = entry.indexOf("\"" + key + "\":") + key.length() + 3;
	    int endIndex = entry.indexOf(",", keyIndex);
	    if (endIndex == -1) {
	        endIndex = entry.indexOf("}", keyIndex);
	        if (endIndex == -1) {
	            endIndex = entry.length(); // , 또는 }가 없을 경우 문자열의 끝까지 사용
	        }
	    }
	    String value = entry.substring(keyIndex, endIndex);
	    return value.replaceAll("\"", ""); // 따옴표 제거
	}
}
