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

	private static final String FORM_VIEW = "/WEB-INF/view/member/loginForm.jsp"; // 로그인 폼의 뷰 페이지 경로를 정의
	private LoginService loginService = new LoginService(); // 로그인 서비스를 처리하는 LoginService 객체를 생성

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { // HTTP 요청을 처리하는 메서드
		if (req.getMethod().equalsIgnoreCase("GET")) { // HTTP 요청 메소드가 GET인 경우 폼을 보여주는 메서드를 호출
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) { // HTTP 요청 메소드가 POST인 경우 로그인을 처리하는 메서드를 호출
			return processSubmit(req, res);
		} else { // 지원하지 않는 HTTP 요청 메소드일 경우 상태 코드를 설정하고 null 반환
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) { // 로그인 폼을 보여주는 메서드
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception { // 로그인을 시도하는 메서드
		// HTTP 요청에서 id와 password 파라미터 추출
		int id = Integer.parseInt(req.getParameter("memberId"));
		String password = trim(req.getParameter("password"));

		Map<String, Boolean> errors = new HashMap<>(); // 에러 메시지를 관리하는 맵을 생성하고 요청 속성으로 설정
		req.setAttribute("errors", errors);

		if (password == null || password.isEmpty()) // id나 password가 비어있으면 에러 메시지 맵에 표시
			errors.put("password", Boolean.TRUE);

		if (!errors.isEmpty()) { // 에러 메시지 맵이 비어있지 않으면 로그인 폼을 다시 보여줌
			return FORM_VIEW;
		}

		try { // LoginService를 사용하여 사용자 로그인을 시도
			User user = loginService.login(id, password);
			req.getSession().setAttribute("authUser", user);
			res.sendRedirect(req.getContextPath() + "/index.jsp"); // 로그인에 성공하면 세션에 사용자 정보를 저장하고 index.jsp로 리다이렉트
			return null;
		} catch (LoginFailException e) { // 로그인에 실패한 경우 에러 메시지를 맵에 추가하고 로그인 폼을 다시 보여줌
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String trim(String str) { // 문자열의 앞뒤 공백을 제거하는 메서드
		return str == null ? null : str.trim();
	}
}