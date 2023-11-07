package item.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ItemSearchRequest;
import item.service.ItemSearchService;
import mvc.command.CommandHandler;

public class ItemSearchHandler implements CommandHandler {

	// ItemSearchService 인스턴스를 생성하고 필드로 유지
	private ItemSearchService searchService = new ItemSearchService();

	// CommandHandler 인터페이스의 메서드를 재정의
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// HTTP 요청에서 "searchType" 및 "searchTerm" 매개변수를 가져옴
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		// ItemSearchRequest 객체를 생성하여 검색 요청을 캡슐화
		ItemSearchRequest searchRequest = new ItemSearchRequest(searchType, searchTerm);
		ItemPage itemPage;

		// "searchType"에 따라 다른 검색 방법을 선택
		if ("item_name".equals(searchType)) {
			// 상품 이름으로 검색하고 결과를 페이징하여 가져옴
			itemPage = searchService.searchItemsByName(searchRequest, 1);
		} else if ("item_class".equals(searchType)) {
			// 상품 클래스로 검색하고 결과를 페이징하여 가져옴
			itemPage = searchService.searchItemsByClass(searchRequest, 1);
		} else {
			// 검색 유형이 지정되지 않은 경우 빈 결과 페이지 생성
			itemPage = new ItemPage(0, 1, Collections.emptyList());
		}

		// 요청 객체에 "itemPage" 속성을 추가하여 JSP에 검색 결과 데이터 전달
		req.setAttribute("itemPage", itemPage);

		// JSP 뷰 페이지의 경로를 반환
		return "/WEB-INF/view/item/ItemSearchForm.jsp";
	}
}
