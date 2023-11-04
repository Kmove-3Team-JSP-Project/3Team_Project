package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler { // CommandHandler 인터페이스 구현

	private static final String FORM_VIEW = "/WEB-INF/view/member/joinForm.jsp";
	private JoinService joinService = new JoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) {
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
		// memberid 자동생성
		try {
			int newMemberCount = joinService.getAutonum();
			req.setAttribute("newMemberCount", newMemberCount);
		} catch (RuntimeException e) {
			// 예외 처리
			e.printStackTrace();
		}
		return FORM_VIEW;
	}

	// 폼 전송 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		JoinRequest joinReq = new JoinRequest(); // JoinRequest 객체 생성
		joinReq.setMemberId(Integer.parseInt(req.getParameter("memberId")));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		joinReq.setMail(req.getParameter("mail"));
		joinReq.setPosition(req.getParameter("position"));

		// 에러 정보를 담을 맴 객체 생성하고 "errors" 속성 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// JoinRequest 객체의 값 검사
		joinReq.validate(errors);

		// errors 맵 객체에 데이터가 존재시 폼 뷰 경로를 리턴
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			// join() 메서드를 실행해 가입 처리 성공시 "/WEB-INF/view/member/loginForm.jsp" 경로 리턴
			joinService.join(joinReq);
			return "/WEB-INF/view/member/loginForm.jsp";
			// 동일 아이디 존재시 DuplicateIdException 발생
		} catch (DuplicateIdException e) {
			// TODO: handle exception
			// errors에 "duplicateId" 키 값 추가
			errors.put("duplicateId", Boolean.TRUE);
			// 폼 뷰 경로 리턴
			return FORM_VIEW;
		}
	}

}
