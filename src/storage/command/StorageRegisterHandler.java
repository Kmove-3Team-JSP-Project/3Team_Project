package storage.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import storage.service.StorageRegisterService;
import storage.service.StorageRequest;

public class StorageRegisterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/storage/storageRegisterForm.jsp";
	private StorageRegisterService storageRegisterService = new StorageRegisterService();

	public String process(HttpServletRequest req, HttpServletResponse resp) {
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {

		try {
			int newStorageCode = storageRegisterService.getAutonum();
			req.setAttribute("newStorageCode", newStorageCode);
		} catch (RuntimeException e) {
			// 예외 처리
			e.printStackTrace();
		}
		return "/WEB-INF/view/storage/storageRegisterForm.jsp";
	}

	public String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		StorageRequest storageReq = new StorageRequest();

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

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		// 비어있는 값이 있는지 확인 하는 메소드 실행.
		storageReq.validate(errors);
		// 값이 비어있으면 조인폼.jsp로 반환.
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// 비어 있지 않으면 가입성공페이지로 반환.
		try {
			storageRegisterService.storageRegister(storageReq);
			return "/storageList.do";
		} catch (Exception e) { // 사용자 아이디가 같으면 예외발생..
			return FORM_VIEW; // 가입페이지로 반환.
		}
	}

}
