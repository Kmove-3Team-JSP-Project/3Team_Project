package auth.service;

public class User {

	private String memberId; 
	private String name; 

	public User(String memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public String getmemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

}
