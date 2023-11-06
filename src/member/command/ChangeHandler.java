package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.model.Member;
import member.service.ChangeService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/member/changeForm.jsp";
	private ChangeService changeSvc = new ChangeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");

		// 에러 정보를 담을 맴 객체 생성하고 "errors" 속성 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		Member changeReq = new Member(user.getmemberId(), req.getParameter("name"), req.getParameter("password"),
				req.getParameter("mail"), req.getParameter("position"));
		// errors 맵 객체에 데이터가 존재시 폼 뷰 경로를 리턴
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			changeSvc.change(changeReq);
			return "/index.jsp";
		} catch (MemberNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
