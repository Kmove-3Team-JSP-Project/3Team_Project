package storage.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import storage.dao.StorageDao;
import storage.model.Storage;

public class StorageSearchHandler implements CommandHandler {
	private StorageDao storageDao = new StorageDao();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse resp) {
    	try {
    		String searchCriteria = request.getParameter("searchCriteria");
    		String searchValue = request.getParameter("searchValue");


    		List<Storage> searchResult = storageDao.searchStorage(ConnectionProvider.getConnection(), searchCriteria, searchValue);
            request.setAttribute("searchResult", searchResult);
            return "/WEB-INF/view/storage/storageSearchForm.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception or redirect to an error page
            return "틀림";
        }
    }
}
