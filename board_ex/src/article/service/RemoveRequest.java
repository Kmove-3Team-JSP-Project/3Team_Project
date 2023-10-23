package article.service;

public class RemoveRequest {

	private String userid;
	private int articleNumber;

	// 메게변수가 있는 생성자
	public RemoveRequest(String userid, int articleNumber) {
		super();
		this.userid = userid;
		this.articleNumber = articleNumber;
	}

	// 메게변수가 없는 생성자
	public RemoveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 게터
	public String getUserid() {
		return userid;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

}
