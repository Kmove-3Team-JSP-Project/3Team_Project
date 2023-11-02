package item.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ItemSearchRequest;
import item.service.ItemSearchService;
import mvc.command.CommandHandler;

public class ItemSearchHandler implements CommandHandler {
	private ItemSearchService searchService = new ItemSearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		ItemSearchRequest searchRequest = new ItemSearchRequest(searchType, searchTerm);
		ItemPage itemPage;

		if ("item_name".equals(searchType)) {
			itemPage = searchService.searchItemsByName(searchRequest, 1);
		} else if ("item_class".equals(searchType)) {
			itemPage = searchService.searchItemsByClass(searchRequest, 1);
		} else {
			itemPage = new ItemPage(0, 1, Collections.emptyList());
		}

		req.setAttribute("itemPage", itemPage);
		return "/WEB-INF/view/item/ItemSearchForm.jsp";
	}
}