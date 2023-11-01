package storage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import storage.service.StorageRequest;
import storage.service.StorageService;

public class StorageListHandler implements CommandHandler {

	private StorageService storageService = new StorageService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		
		StorageRequest storageRequest = storageService.getReadStorage();
		req.setAttribute("storageRequset", storageRequest);
		return "/WEB-INF/view/storage/storageListForm.jsp";
	}

}
