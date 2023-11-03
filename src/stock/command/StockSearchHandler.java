package stock.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockPage;
import stock.service.StockSearchRequest;
import stock.service.StockSearchService;

public class StockSearchHandler implements CommandHandler {
	private StockSearchService searchService = new StockSearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		StockSearchRequest searchRequest = new StockSearchRequest(searchType, searchTerm);
		StockPage stockPage;

		if ("stock_name".equals(searchType)) {
			stockPage = searchService.searchStockByStock(searchRequest, 1);
		} else if ("storage_name".equals(searchType)) {
			stockPage = searchService.searchStockByStorage(searchRequest, 1);
		} else {
			stockPage = new StockPage(0, 1, Collections.emptyList());
		}

		req.setAttribute("stockPage", stockPage);
		return "/WEB-INF/view/stock/StockSearchForm.jsp";

	}

}
