package recall.service;

import java.util.List;

import recall.model.Recall;

public class RecallPage {
	private int total;
	private int currentPage;
	private List<Recall> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public RecallPage(int total, int currentPage, int size, List<Recall> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;

			endPage = startPage + 5;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}

	public boolean hasNoRecall() {
		return total == 0;
	}

	public boolean hasdRecall() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Recall> getContent() {
		return content;
	}

	public int getTotalPage() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}
