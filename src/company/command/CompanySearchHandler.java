package company.command;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.service.CompanyPage;
import company.service.CompanySearchRequest;
import company.service.CompanySearchService;
import mvc.command.CommandHandler;

public class CompanySearchHandler implements CommandHandler {

	private CompanySearchService searchService = new CompanySearchService();// CompanyService 클래스는 데이터베이스 연동을 담당합니다.

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		CompanySearchRequest searchRequest = new CompanySearchRequest(searchType, searchTerm);
		CompanyPage companyPage;

		if ("company_Name".equals(searchType)) {
			companyPage = searchService.searchItemsByName(searchRequest, 1);
		} else if ("company_class".equals(searchType)) {
			companyPage = searchService.searchCompaniesByClass(searchRequest, 1);
		} else {
			companyPage = new CompanyPage(0, 1, Collections.emptyList());
		}

		req.setAttribute("companyPage", companyPage);
		return "/WEB-INF/view/company/CompanySearchForm.jsp";

	}
}
