package order.command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import company.model.Company;
import item.model.Item;
import mvc.command.CommandHandler;
import order.service.OrderRegisterService;
import order.service.OrderRequest;
import storage.model.Storage;

public class OrderRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "WEB-INF/view/orderRegisterForm.jsp";
	private OrderRegisterService regiService = new OrderRegisterService();
	
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
		int orderNo = regiService.getOrderNo();
        req.setAttribute("orderNo", orderNo);
        List<Item> itemList = regiService.getItemList(); // Item 클래스에는 itemName과 unitPrice가 있어야 함

     // itemList를 JSON 형식의 문자열로 변환
     String itemListJson = "["; // JSON 배열의 시작
     for (Item item : itemList) {
         itemListJson += "{\"itemName\":\"" + item.getItemName() + "\",\"unitPrice\":" + item.getUnitPrice() + "},";
     }
     itemListJson = itemListJson.substring(0, itemListJson.length() - 1); // 마지막 쉼표 제거
     itemListJson += "]"; // JSON 배열의 끝

     req.setAttribute("itemListJson", itemListJson); // itemListJson을 JSP로 전달
        
     List<Company> companyList = regiService.getCompanyList();
     req.setAttribute("companyList", companyList);
     List<Storage> storageList = regiService.getStorageList();
     req.setAttribute("storageList", storageList);
     
        return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
	
		User user = (User) req.getSession(false).getAttribute("authUser");
		OrderRequest orderReq = createOrderRequest(user, req);
		orderReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		return "/WEB-INF/view/orderListForm.jsp";
	}

	public Date transformDate(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date orderDate = (Date)dateFormat.parse(d);
			return orderDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	private OrderRequest createOrderRequest(User user, HttpServletRequest req) {
		return new OrderRequest(Integer.parseInt(req.getParameter("orderNo")), user.getName(), req.getParameter("itemName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("orderDate")), req.getParameter("progress"));
	}
}