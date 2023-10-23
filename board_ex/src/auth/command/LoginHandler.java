package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
			// GET POST 둘다 아니면 405 응답 코드 전송
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// processForm 폼을 보여주는 뷰 경로 리턴
	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	// 폼 전송 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));

		// 에러 정보를 담을 맴 객체 생성하고 "errors" 속성 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if (password == null || password.isEmpty())
			errors.put("password", Boolean.TRUE);

		// errors 맵 객체에 데이터가 존재시 폼 뷰 경로를 리턴
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			User user = loginService.login(id, password);
			req.getSession().setAttribute("authUser", user);
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return null;
		} catch (LoginFailException e) {
			// TODO: handle exception
			// errors에 "" 키 값 추가
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			// 폼 뷰 경로 리턴
			return FORM_VIEW;
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}

}
