package company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.service.CompanyPage;
import company.service.CompanyService;
import mvc.command.CommandHandler;

public class CompanyListHandler implements CommandHandler {
	private CompanyService companyService = new CompanyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		CompanyPage companyPage = companyService.getCompanyPage(pageNo);
		req.setAttribute("companyPage", companyPage);

		return "/WEB-INF/view/company/CompanyListForm.jsp";
	}

}