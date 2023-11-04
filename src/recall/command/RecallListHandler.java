package recall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import recall.service.RecallPage;
import recall.service.RecallService;

public class RecallListHandler implements CommandHandler {
	private RecallService recallService = new RecallService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		RecallPage recallPage = recallService.getRecallPage(1);
		req.setAttribute("recallPage", recallPage);
		return "/WEB-INF/view/recall/RecallListForm.jsp";
	}

}
