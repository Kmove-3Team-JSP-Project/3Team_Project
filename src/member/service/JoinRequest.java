package member.service;

import java.util.Map;

public class JoinRequest {

	private int memberId;
	private String name;
	private String password;
	private String confirmPassword;
	private String mail;
	private String position;

	// 게터세터
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	// password(암호)와 confirmPassword(확인)이 같은지 검사
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}

	// 각 필드의 데이터가 유효한지 검사
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, Integer.toString(memberId), "memberId");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		checkEmpty(errors, mail, "mail");
		checkEmpty(errors, position, "position");

		if (!errors.containsKey("confirmPassword")) {
			// isPasswordEqualToConfirm 메서드를 이용해 암호와
			// 확인값이 일치하지 않으면 notMatch 에러 키 추가
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

	// value가 값이 없는경우 errors 맵 객체에 fieldName 키에 TRUE 값 추가
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

}
