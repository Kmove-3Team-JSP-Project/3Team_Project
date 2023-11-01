package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import mvc.command.CommandHandler;
	
private ItemService itemService = new ItemService();

public class ItemListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		ItemPage itemPage = itemService.getItemPage(1); 
		req.setAttribute("itemPage", itemPage);
	return "/WEB-INF/view/ItemListForm.jsp";
	}
	
}
