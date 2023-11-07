package buy.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buy.service.BuyPage;
import buy.service.BuySearchRequest;
import buy.service.BuySearchService;
import mvc.command.CommandHandler;

public class BuySearchHandler implements CommandHandler {
	private BuySearchService searchService = new BuySearchService();// CompanyService 클래스는 데이터베이스 연동을 담당합니다.

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		BuySearchRequest searchRequest = new BuySearchRequest(searchType, searchTerm);
		BuyPage buyPage;

		if ("company_Name".equals(searchType)) {
			buyPage = searchService.searchItemsByName(searchRequest, 1);
		} else if ("master".equals(searchType)) {
			buyPage = searchService.searchCompaniesByMember(searchRequest, 1);
		} else {
			buyPage = new BuyPage(0, 1, Collections.emptyList());
		}

		req.setAttribute("buyPage", buyPage);
		return "/WEB-INF/view/buy/BuySearchForm.jsp";

	}
}
