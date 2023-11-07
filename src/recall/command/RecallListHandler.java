package recall.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import recall.service.RecallPage;
import recall.service.RecallService;

public class RecallListHandler implements CommandHandler {

    private RecallService recallService = new RecallService(); // RecallService 인스턴스를 생성하여 RecallListHandler 클래스 내에서 사용할 수 있도록 하는 필드

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO Auto-generated method stub
        // RecallService를 사용하여 페이지 번호 1로 RecallPage를 가져옵니다.
        RecallPage recallPage = recallService.getRecallPage(1);
        // 가져온 RecallPage를 request 속성에 설정하여 JSP에서 사용할 수 있도록 합니다.
        req.setAttribute("recallPage", recallPage);
        // JSP 페이지의 경로를 반환하여 클라이언트에게 보여줄 화면을 지정합니다.
        return "/WEB-INF/view/recall/RecallListForm.jsp";
    }
}

