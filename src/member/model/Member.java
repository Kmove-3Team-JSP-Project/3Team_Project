package member.model;

import java.util.Date;

public class Member {

	private int memberid;
	private String name;
	private String password;
	private String mail;
	private String position;

	// 매개변수가 있는 생정사
	public Member(int memberid, String name, String password, String mail, String position) {
		super();
		this.memberid = memberid;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.position = position;
	}

	// 매개변수가 없는 생성자
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 게터
	public int getMemberid() {
		return memberid;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getMail() {
		return mail;
	}

	public String getPosition() {
		return position;
	}

	// 암호 확인
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	// 암호 변경 기능 구현시 사용
	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

}
