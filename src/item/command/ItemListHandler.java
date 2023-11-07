package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ItemService;
import mvc.command.CommandHandler;

public class ItemListHandler implements CommandHandler {

	// ItemService 인스턴스를 생성하고 필드로 유지
	private ItemService itemService = new ItemService();

	// CommandHandler 인터페이스의 메서드를 재정의
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		// ItemService를 사용하여 첫 번째 페이지의 ItemPage 객체를 가져옴
		ItemPage itemPage = itemService.getItemPage(1);

		// 요청 객체에 "itemPage" 속성을 추가하여 JSP에 데이터 전달
		req.setAttribute("itemPage", itemPage);

		// JSP 뷰 페이지의 경로를 반환
		return "/WEB-INF/view/item/ItemListForm.jsp";
	}
}
