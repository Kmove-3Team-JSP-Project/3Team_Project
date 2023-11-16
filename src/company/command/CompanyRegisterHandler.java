// CompanyRegisterHandler.java
package company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Company;
import company.service.CompanyRegisterService;
import mvc.command.CommandHandler;

public class CompanyRegisterHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/company/CompanyRegisterForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
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
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception { // 로그인을 시도하는 메서드
		
		CompanyRegisterService service = new CompanyRegisterService();
		
		String company_No = req.getParameter(""); // int 타입으로 변환 필수
		String company_Name =req.getParameter("company_name");
		String master = req.getParameter("master");
		String phone =req.getParameter("phone");
		String address =req.getParameter("address");
		String myStorage =req.getParameter("myStorage");
		
		int iCompanyNo =0;
		if(company_No != null) {
			iCompanyNo = Integer.parseInt(company_No);
		}
			
		
		Company data =  new Company(123,company_Name, master,phone,address,myStorage);
		service.register(data);
		
		return FORM_VIEW;
	}

}
