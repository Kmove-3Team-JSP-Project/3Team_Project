package sheet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sheet.service.SheetListService;
import sheet.service.SheetPage;

public class SheetListHandler implements CommandHandler {
	private SheetListService listService = new SheetListService();
	private static final String FORM_VIEW = "/WEB-INF/view/sheet/sheetListForm.jsp";
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String pageNoVal = req.getParameter("pageNo"); 
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal); 
		}
		SheetPage sheetPage = listService.getSheetPage(pageNo); // 게시글 데이터 저장
		
		req.setAttribute("sheetPage", sheetPage); // JSP에서 사용하도록 속성 저장
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	      return FORM_VIEW;
	    
	}

}
