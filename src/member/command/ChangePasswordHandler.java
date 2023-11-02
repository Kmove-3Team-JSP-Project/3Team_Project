package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		// TODO Auto-generated method stub
		// 요청방식이 GET 이면 processForm 실행
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
			// 요청 방식이 POST 이면 processSubmit 실행
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
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");

		// 에러 정보를 담을 맴 객체 생성하고 "errors" 속성 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");

		if (curPwd == null || curPwd.isEmpty()) {
			errors.put("curPwd", Boolean.TRUE);
		}
		if (curPwd == null || curPwd.isEmpty()) {
			errors.put("newPwd", Boolean.TRUE);
		}

		// errors 맵 객체에 데이터가 존재시 폼 뷰 경로를 리턴
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			changePwdSvc.changePassword(user.getmemberId(), curPwd, newPwd);
			return "/WEB-INF/view/changePwdSuccess.jsp";
		} catch (InvalidPasswordException e) {
			// TODO: handle exception
			// errors에 "badCurPwd" 키 값 추가
			errors.put("badCurPwd", Boolean.TRUE);
			// 폼 뷰 경로 리턴
			return FORM_VIEW;
		} catch (MemberNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
