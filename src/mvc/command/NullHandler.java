package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 404 에러 응답으로 전송하는 핸들러 클래스
public class NullHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
}
