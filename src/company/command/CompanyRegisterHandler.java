package company.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.service.CompanyRegisterService;
import company.service.CompanyService;
import mvc.command.CommandHandler;

public class CompanyRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/company/CompanyRegisterForm.jsp";
	private CompanyRegisterService companyRegisterService = new CompanyRegisterService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		return FORM_VIEW;

	}
}
