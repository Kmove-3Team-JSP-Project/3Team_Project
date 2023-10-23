package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// CommandHandler 인터페이스를 정의
public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
