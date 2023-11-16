package company.command;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import company.service.CompanyPage;
import company.service.CompanySearchRequest;
import company.service.CompanySearchService;
import mvc.command.CommandHandler;

public class CompanySearchHandler implements CommandHandler {

	private CompanySearchService searchService = new CompanySearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)throws Exception {
		// 사용자로부터의 입력 값 얻기
		String searchType = req.getParameter("searchType");
		String searchTerm = req.getParameter("searchTerm");

		// 검색을 위한 요청 객체 생성
		CompanySearchRequest searchRequest = new CompanySearchRequest(searchType, searchTerm);
		CompanyPage companyPage;

		// 검색 타입에 따라 서비스 호출
		if ("company_Name".equals(searchType)) {
			// 회사 이름으로 검색
			companyPage = searchService.searchCompanyName(searchRequest, 1);
		} else if ("master".equals(searchType)) {
			// 담당자로 검색
			companyPage = searchService.searchCompanybyMaster(searchRequest, 1);
		} else {
			// 검색 유형이 지정되지 않은 경우 빈 결과 페이지 생성
			companyPage = new CompanyPage(0, 1, Collections.emptyList());
		}

		// 검색 결과를 JSP로 전달하기 위해 Request에 속성 추가
		req.setAttribute("companyPage", companyPage);

		// 결과 페이지 JSP의 경로 반환
		return "/WEB-INF/view/company/CompanySearchForm.jsp";
	}
}
