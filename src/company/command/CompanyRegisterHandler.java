package company.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class CompanyRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/company/CompanyRegisterForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res); // HTTP GET 요청 처리
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // HTTP POST 요청 처리
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		return "/WEB-INF/view/company/CompanySearchform.jsp";
	}
}