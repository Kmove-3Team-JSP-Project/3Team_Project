package storage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.command.CommandHandler;
import storage.service.StoragePage;
import storage.service.StorageService;

public class StorageListHandler implements CommandHandler {

	private StorageService storageService = new StorageService();
	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		StoragePage storagePage = storageService.getStoragePage(pageNo);
		req.setAttribute("storagePage", storagePage);
		return "/WEB-INF/view/listArticle.jsp";
	}

}
