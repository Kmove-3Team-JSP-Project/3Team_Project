package recall.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import recall.service.RecallListService;
import recall.service.RecallPage;

public class RecallListHandler implements CommandHandler {
	private RecallListService listService = new RecallListService();
	private static final String FORM_VIEW = "/WEB-INF/view/recall/RecallListForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		RecallPage recallPage = listService.getRecallPage(pageNo);

		req.setAttribute("recallPage", recallPage);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String recallNoVal = req.getParameter("recallNo");
		String process = req.getParameter("process");

		if (recallNoVal == null || process == null) {
			return FORM_VIEW;
		}

		int recallNo = Integer.parseInt(recallNoVal);

		Integer updateSuccess = listService.updateRecallProcess(recallNo, process);

		if (updateSuccess > 0) {
			return FORM_VIEW;
		} else {
			req.setAttribute("updateFailed", true);
			return FORM_VIEW;
		}

	}
}
