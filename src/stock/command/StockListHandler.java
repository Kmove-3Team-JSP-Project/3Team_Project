package stock.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockPage;
import stock.service.StockService;

public class StockListHandler implements CommandHandler {
	private StockService stockService = new StockService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		StockPage stockPage = stockService.getStockPage(1);
		req.setAttribute("stockPage", stockPage);
		return "/WEB-INF/view/stock/StockListForm.jsp";
	}

}
