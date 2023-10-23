package article.service;

import java.util.Map;

public class ModifyRequest {

	private String userid;
	private int articleNumber;
	private String title;
	private String content;

	public ModifyRequest(String userid, int articleNumber, String title, String content) {
		super();
		this.userid = userid;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
	
}
