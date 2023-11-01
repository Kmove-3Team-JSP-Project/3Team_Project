package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false); // 현재 사용자 세션을 가져오거나 새 세션을 생성하지 않고 가져오기
		if (session != null) { // 세션이 null이 아닌 경우 세션을 무효화(로그아웃)
			session.invalidate();
		}
		res.sendRedirect(req.getContextPath() + "/index.jsp"); // 사용자를 로그아웃한 후에는 index.jsp 페이지로 리다이렉트
		return null; // 로그아웃을 한 후, null 반환(이후의 동작에 영향을 미치지 않으므로)
	}

}
