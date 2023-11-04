package recall.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import recall.service.RecallRegisterRequest;

public class RecallRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/recall/RecallRegister.jsp";
	
	private RecallRegisterService recallRegisterservice = new RecallRegisterService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res); s
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSumbit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String am = req.getParameter("amount");
		
			RecallReigsterRequest recallReq = new RecallRegisterRequest(); 
			
		}
	}
}
