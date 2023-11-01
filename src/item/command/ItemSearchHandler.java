package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ItemSearchService;
import mvc.command.CommandHandler;

public class ItemSearchHandler implements CommandHandler {
	private ItemSearchService itemSearchService = new ItemSearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");
		int pageNumber = 1;

		ItemPage searchResult = itemSearchService.searchItems(searchType, searchTerm, pageNumber);

		req.setAttribute("searchResult", searchResult);

		return "/WEB-INF/view/item/ItemSearchForm.jsp";
	}
}