package company.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.service.CompanyService;
import mvc.command.CommandHandler;

public class CompanyRegisterHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			// GET 요청인 경우, 등록 폼을 표시
			return "/WEB-INF/view/company/CompanyRegisterForm.jsp";
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			// POST 요청인 경우, 거래처 정보를 등록

			// 폼에서 입력된 데이터 가져오기
			String companyNoStr = req.getParameter("company_No");
			if (companyNoStr != null && !companyNoStr.isEmpty()) {
				try {
				} catch (NumberFormatException e) {
					// 숫자로 변환할 수 없는 경우 또는 오류 처리
				}
			}
			// 이제 companyNo 변수에 int 값이 들어 있습니다.
			String company_No = req.getParameter("company_No");
			String company_Name = req.getParameter("company_Name");
			String master = req.getParameter("master");
			String phone = req.getParameter("phone");
			String myStorage = req.getParameter("myStorage");

			// 여기에서 거래처 정보를 데이터베이스에 저장하는 등의 로직을 구현합니다.
			CompanyService.registerCompany(company_No,company_Name, master, phone, myStorage);

			// 등록 후 리다이렉션할 경로를 반환 (예: 목록 페이지)
			return "redirect:/WEB-INF/view/company/CompanylistForm.jsp";
		}

		return null;
	}
}
