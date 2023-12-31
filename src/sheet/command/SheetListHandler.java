package sheet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sheet.service.SheetListService;
import sheet.service.SheetPage;

public class SheetListHandler implements CommandHandler {
	private SheetListService listService = new SheetListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		SheetPage sheetPage = listService.getSheetPage(pageNo); // 게시글 데이터 저장
		
		req.setAttribute("sheetPage", sheetPage); // JSP에서 사용하도록 속성 저장
		return "/WEB-INF/view/sheet/sheetListForm.jsp";
	}

}
