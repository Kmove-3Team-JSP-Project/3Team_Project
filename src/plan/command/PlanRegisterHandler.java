package plan.command;

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
import plan.service.PlanRegisterService;
import plan.service.PlanRequest;
import sheet.service.SheetRequest;

public class PlanRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/plan/planRegisterForm.jsp";
	private PlanRegisterService regiService = new PlanRegisterService();
	
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
		
		int planNo = regiService.getPlanNo();
        req.setAttribute("planNo", planNo);
        
        Map<String, Integer> stockDetails = regiService.getStockNamesWithUnitPrice();
        String stockDetailsJson = getItemDetailsJson(stockDetails);
        req.setAttribute("stockDetailsJson", stockDetailsJson);
        
        List<String> stockNames = regiService.getAllStockNames();
        req.setAttribute("stockNames", stockNames);
        
        List<String> companyLists = regiService.getCompanyList();
        req.setAttribute("companyLists", companyLists);
        
        List<String> storageLists = regiService.getStorageList();
        req.setAttribute("storageLists", storageLists);

        return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		PlanRequest planReq = createPlanRequest(req);
		SheetRequest sheetReq = createSheetRequest(req);
		planReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newPlanNo = regiService.register(planReq);
		int newSheetNo = regiService.registerSheet(sheetReq);
		
		
		req.setAttribute("newPlanNo", newPlanNo);
		req.setAttribute("newSheetNo", newSheetNo);
		return "/WEB-INF/view/plan/planRegisterSuccess.jsp";
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

	private PlanRequest createPlanRequest(HttpServletRequest req) {
		return new PlanRequest(Integer.parseInt(req.getParameter("planNo")), req.getParameter("name"), req.getParameter("stockName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("planDate")), req.getParameter("ending"));
	}
	
	private SheetRequest createSheetRequest(HttpServletRequest req) {
		return new SheetRequest(Integer.parseInt(req.getParameter("planNo")), req.getParameter("name"), req.getParameter("stockName"),
				Integer.parseInt(req.getParameter("unitPrice")), Integer.parseInt(req.getParameter("amount")),
				Integer.parseInt(req.getParameter("price")), req.getParameter("companyName"),
				req.getParameter("storageName"), transformDate(req.getParameter("planDate")), req.getParameter("ending"));
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
