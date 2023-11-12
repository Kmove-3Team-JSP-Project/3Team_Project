package plan.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import plan.service.PlanListService;
import plan.service.PlanPage;

public class PlanListHandler implements CommandHandler {
	private PlanListService listService = new PlanListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		PlanPage planPage = listService.getPlanPage(pageNo); // 게시글 데이터 저장

		req.setAttribute("planPage", planPage); // JSP에서 사용하도록 속성 저장
		return "/WEB-INF/view/plan/planListForm.jsp";
	}

}
