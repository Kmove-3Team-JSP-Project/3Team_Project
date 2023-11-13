package plan.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import plan.service.PlanListService;
import plan.service.PlanPage;
import plan.service.PlanUpdateService;

public class PlanCheckHandler implements CommandHandler {

	private PlanListService listService = new PlanListService();
	private PlanUpdateService UpdateService = new PlanUpdateService();

	private static final String FORM_VIEW = "/WEB-INF/view/plan/planCheckForm.jsp";

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
		PlanPage planPage = listService.getPlanCheckPage(pageNo); // 게시글 데이터 저장

		req.setAttribute("planPage", planPage); // JSP에서 사용하도록 속성 저장
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String jsonData = req.getParameter("planNoArray");
		System.out.println(jsonData);

		if (jsonData == null || jsonData.isEmpty()) {
			// 유효하지 않은 데이터
			return FORM_VIEW;
		}

		// 대괄호를 제거하고 각 항목으로 분할
		String[] entries = jsonData.substring(1, jsonData.length() - 1).split("\\},");

		int updateSuccess = 0;

		for (String entry : entries) {
			// orderNo 및 progress 추출
			int planNo = extractValue(entry, "planNo");
			String ending = extractValueString(entry, "ending");

			// 시트 상태 업데이트
			int sheetUpdateResult = UpdateService.updateSheetEnding(planNo, ending);
			System.out.println(ending);

			// 주문 상태 업데이트
			int planUpdateResult = UpdateService.updatePlanEnding(planNo, ending);

			// 각 주문 번호에 대한 결과를 개별적으로 저장
			req.setAttribute("planUpdateResult_" + planNo, planUpdateResult);
			req.setAttribute("sheetUpdateResult_" + planNo, sheetUpdateResult);

			updateSuccess++; // 업데이트 성공 횟수 증가
		}

		if (updateSuccess > 0) {
			// 업데이트가 성공적으로 처리되었을 때의 처리
			return "/WEB-INF/view/plan/planCheckSuccess.jsp"; // 성공 화면으로 리다이렉트 또는 포워딩
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
		String value = entry.substring(keyIndex, endIndex).replaceAll("\"", "").trim(); // 따옴표 제거
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
	    String value = entry.substring(keyIndex, endIndex).replaceAll("\"", "").trim(); // 따옴표 제거
	    return value;
	}
}