package plan.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import plan.service.PlanListService;
import plan.service.PlanPage;

public class PlanSearchHandler implements CommandHandler {
	private PlanListService listService = new PlanListService();


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String condition =	req.getParameter("condition");
		String detail = req.getParameter("detail").trim();
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		
		PlanPage planPage = listService.getPlanSearchPage(condition, detail, pageNo); // 게시글 데이터 저장	
		req.setAttribute("planPage", planPage);
		req.setAttribute("condition", condition);
		req.setAttribute("detail", detail);
		return "/WEB-INF/view/plan/planSearchForm.jsp";
	}
}
