package order.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import order.service.OrderRegisterService;
import order.service.OrderRequest;
import sheet.service.SheetRequest;

public class OrderRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/order/orderRegisterForm.jsp";
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
		User user = (User)req.getSession().getAttribute("authUser");
		req.setAttribute("authUser", user);
		
		int orderNo = regiService.getOrderNo();
        req.setAttribute("orderNo", orderNo);
        
        Map<String, Integer> itemDetails = regiService.getItemNamesWithUnitPrice();
        String itemDetailsJson = getItemDetailsJson(itemDetails);
        req.setAttribute("itemDetailsJson", itemDetailsJson);
        
        List<String> itemNames = regiService.getAllItemNames();
        req.setAttribute("itemNames", itemNames);
        
        List<String> companyLists = regiService.getCompanyList();
        req.setAttribute("companyLists", companyLists);
        
        List<String> storageLists = regiService.getStorageList();
        req.setAttribute("storageLists", storageLists);

        return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		OrderRequest orderReq = createOrderRequest(req);
		SheetRequest sheetReq = createSheetRequest(req);
		orderReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newOrderNo = regiService.register(orderReq);
		int newSheetNo = regiService.registerSheet(sheetReq);
		
		
		req.setAttribute("newOrderNo", newOrderNo);
		req.setAttribute("newSheetNo", newSheetNo);
		return "/WEB-INF/view/order/orderRegisterSucess.jsp";
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

	private OrderRequest createOrderRequest(HttpServletRequest req) {
		return new OrderRequest(Integer.parseInt(req.getParameter("orderNo")), req.getParameter("name"), req.getParameter("itemName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("orderDate")), req.getParameter("progress"));
	}
	
	private SheetRequest createSheetRequest(HttpServletRequest req) {
		return new SheetRequest(Integer.parseInt(req.getParameter("orderNo")), req.getParameter("name"), req.getParameter("itemName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("orderDate")), req.getParameter("progress"));
	}
	
	
	
	public String getItemDetailsJson(Map<String, Integer> itemDetails) {
	    StringBuilder jsonBuilder = new StringBuilder("{");
	    
	    for (Map.Entry<String, Integer> entry : itemDetails.entrySet()) {
	        jsonBuilder.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
	    }
	    
	    if (!itemDetails.isEmpty()) {
	        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // 마지막 쉼표 제거
	    }
	    
	    jsonBuilder.append("}");
	    
	    return jsonBuilder.toString();
	}


}