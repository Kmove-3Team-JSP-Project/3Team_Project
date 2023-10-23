package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import auth.service.User;
import auth.service.WriteArticleService;
import auth.service.WriteRequest;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/newArticleForm.jsp";
	private WriteArticleService writeArticleService = new WriteArticleService();

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User) req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newArticleNo = writeArticleService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);

		return "/WEB-INF/view/newArticleSuccess.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getName()), req.getParameter("title"),
				req.getParameter("content"));
	}
}
