package stock.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockPage;
import stock.service.StockSearchRequest;
import stock.service.StockSearchService;

public class StockSearchHandler implements CommandHandler {

	// StockSearchService 인스턴스를 생성하고 필드로 유지
	private StockSearchService searchService = new StockSearchService();

	// CommandHandler 인터페이스의 메서드를 재정의
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// HTTP 요청에서 "searchType" 및 "searchTerm" 매개변수를 가져옴
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		// StockSearchRequest 객체를 생성하여 검색 요청을 캡슐화
		StockSearchRequest searchRequest = new StockSearchRequest(searchType, searchTerm);
		StockPage stockPage;

		// "searchType"에 따라 다른 검색 방법을 선택
		if ("stock_name".equals(searchType)) {
			// 재고 이름으로 검색하고 결과를 페이징하여 가져옴
			stockPage = searchService.searchStockByStock(searchRequest, 1);
		} else if ("storage_name".equals(searchType)) {
			// 저장소 이름으로 검색하고 결과를 페이징하여 가져옴
			stockPage = searchService.searchStockByStorage(searchRequest, 1);
		} else {
			// 검색 유형이 지정되지 않은 경우 빈 결과 페이지 생성
			stockPage = new StockPage(0, 1, Collections.emptyList());
		}

		// 요청 객체에 "stockPage" 속성을 추가하여 JSP에 검색 결과 데이터 전달
		req.setAttribute("stockPage", stockPage);

		// JSP 뷰 페이지의 경로를 반환
		return "/WEB-INF/view/stock/StockSearchForm.jsp";
	}
}
