package company.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Company;
import company.service.CompanyService;
import mvc.command.CommandHandler;

public class CompanySearchHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/company/CompanySearchForm.jsp";
	private CompanyService companyService; // CompanyService 클래스는 데이터베이스 연동을 담당합니다.

	public CompanySearchHandler() {
		// CompanyService 클래스의 인스턴스를 생성
		companyService = new CompanyService();
	}

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
		// 검색 폼에서 입력된 검색 조건을 읽어옴
		String searchKeyword = req.getParameter("searchKeyword");

		// CompanyService를 사용하여 데이터베이스에서 검색 결과를 가져옴
		List<Company> searchResults = companyService.(searchKeyword);

		// 검색 결과를 request에 저장
		req.setAttribute("searchResults", searchResults);

		return "/WEB-INF/view/company/SearchResult.jsp"; // 검색 결과를 보여줄 뷰 페이지 경로
	}
}
