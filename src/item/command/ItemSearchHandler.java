package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ItemSearchService;
import mvc.command.CommandHandler;

public class ItemSearchHandler implements CommandHandler {
	private ItemSearchService searchService = new ItemSearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		ItemSearchRequest searchRequest = new ItemSearchRequest(searchType, searchTerm);
		ItemPage itemPage = searchService.searchItems(searchRequest, 1);

		req.setAttribute("itemPage", itemPage);
		return "/WEB-INF/view/item/ItemSearchForm.jsp";
	}
}