package auth.service;

public class User {

	private int memberId; 
	private String name; 

	public User(int memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public int getmemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

}
