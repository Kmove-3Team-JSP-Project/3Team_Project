package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFountException;
import article.service.RemoveRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import article.service.RemoveArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class RemoveArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/removeForm.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private RemoveArticleService removeService = new RemoveArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// 폼 요청 처리
	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);

			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			// 게시글 작성자가 아니면 403 응답
			if (!canRemove(authUser, articleData)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			// 폼에 데이터를 보여줄 때 사용할 객체를 생성 
			RemoveRequest remReq = new RemoveRequest(authUser.getId(), no);
			// request의 remReq 속성에 저장
			req.setAttribute("remReq", remReq);
			// 뷰 리턴
			return FORM_VIEW; // 
		} catch (ArticleNotFountException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canRemove(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

	// 폼 전송 처리
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		RemoveRequest remReq = new RemoveRequest(authUser.getId(), no);
		req.setAttribute("remReq", remReq);

		// 에러 정보를 담을 맵 객체 생성
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// 게시글 수정 기능 실행
		try {
			removeService.remove(remReq);
			return "/WEB-INF/view/removeSuccess.jsp";
		} catch (ArticleContentNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
