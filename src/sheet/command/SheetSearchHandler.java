package sheet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sheet.service.SheetListService;
import sheet.service.SheetPage;

public class SheetSearchHandler implements CommandHandler{
	private SheetListService listService = new SheetListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String condition =	req.getParameter("condition");
		String detail = req.getParameter("detail").trim();
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		
		SheetPage sheetPage = listService.getSheetSearchPage(condition, detail, pageNo); // 게시글 데이터 저장	
		req.setAttribute("sheetPage", sheetPage);
		req.setAttribute("condition", condition);
		req.setAttribute("detail", detail);
		return "/WEB-INF/view/sheet/sheetSearchForm.jsp";
	}

}
