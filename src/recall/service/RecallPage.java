package recall.service;

import java.util.Collections;
import java.util.List;

import recall.model.Recall;

public class RecallPage {
	private int total;
	private int currentPage;
	private List<Recall> content;

	public RecallPage(int total, int currentPage, List<Recall> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		Collections.sort(content,
				(recall1, recall2) -> Integer.compare(recall1.getRecall_No(), recall2.getRecall_No()));
		this.content = content;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Recall> getContent() {
		return content;
	}

	public void setContent(List<Recall> content) {
		this.content = content;
	}

}
