package stock.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockPage;
import stock.service.StockService;

public class StockListHandler implements CommandHandler {

	// StockService 인스턴스를 생성하고 필드로 유지
	private StockService stockService = new StockService();

	// CommandHandler 인터페이스의 메서드를 재정의
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// StockService를 사용하여 첫 번째 페이지의 StockPage 객체를 가져옴
		StockPage stockPage = stockService.getStockPage(1);

		// 요청 객체에 "stockPage" 속성을 추가하여 JSP에 데이터 전달
		req.setAttribute("stockPage", stockPage);

		// JSP 뷰 페이지의 경로를 반환
		return "/WEB-INF/view/stock/StockListForm.jsp";
	}
}
