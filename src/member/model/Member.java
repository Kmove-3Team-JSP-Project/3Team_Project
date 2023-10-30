package member.model;

import java.util.Date;

public class Member {

	private String id;
	private String name;
	private String password;
	private Date regDate;

	// 매개변수가 있는 생정사
	public Member(String id, String name, String password, Date regDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}

	// 매개변수가 없는 생성자
	public Member() {
		super();
	}

	// 게터
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegDate() {
		return regDate;
	}

	// 암호 변경 기능 구현시 사용
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

}
