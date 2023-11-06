package buy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buy.service.BuyPage;
import buy.service.BuyService;
import mvc.command.CommandHandler;

public class BuyListHandler implements CommandHandler {
	private BuyService BuyService = new BuyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		BuyPage buyPage = BuyService.getBuyPage(pageNo);
		req.setAttribute("buyPage", buyPage);

		return "/WEB-INF/view/buy/BuyListForm.jsp";
	}

}