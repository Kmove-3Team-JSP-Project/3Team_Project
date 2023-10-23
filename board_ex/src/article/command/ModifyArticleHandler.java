package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFountException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);

			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canModify(authUser, articleData)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, articleData.getArticle().getTitle(),
					articleData.getContent());

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (ArticleNotFountException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canModify(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, req.getParameter("title"),
				req.getParameter("content"));
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (ArticleContentNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
