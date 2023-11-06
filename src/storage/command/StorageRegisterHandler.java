package storage.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import storage.service.StorageRequest;
import ticket.service.TicketRequest;

public class StorageRegisterHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		StorageRequest storageReq = new StorageRequest();
		//입력 받은 값을 각각의 객체에 할당.
	
		String storageIdParam = req.getParameter("storage_id");
		int storageCode = 0; // 기본값 설정

		if (storageIdParam != null && !storageIdParam.isEmpty()) {
		    try {
		        storageCode = Integer.parseInt(storageIdParam);
		    } catch (NumberFormatException e) {
		        // 변환 중 오류 발생 시 예외 처리
		    }
		}

		storageReq.setStorageCode(storageCode);
		storageReq.setStorageName(req.getParameter("storage_name"));
		storageReq.setStorageAddress(req.getParameter("storage_Address"));
		storageReq.setStorageUse(req.getParameter("Use"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
		Date startDate = dateFormat.parse(req.getParameter("startdate"));
        Date endDate = dateFormat.parse(req.getParameter("enddate"));
        ticketReq.setStartDate(startDate);
        ticketReq.setEndDate(endDate);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		//비어있는 값이 있는지 확인 하는 메소드 실행.
		ticketReq.validate(errors);
		//값이 비어있으면 조인폼.jsp로 반환.
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//비어 있지 않으면 가입성공페이지로 반환.
		try {
			ticketService.sighUp(ticketReq);
			return "/WEB-INF/view/ticketRead.jsp";
		}catch(Exception e) { // 사용자 아이디가 같으면 예외발생..
			return FORM_VIEW; // 가입페이지로 반환.
		}
	}

}
