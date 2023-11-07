package buy.service;

import java.util.Collections;
import java.util.List;

import buy.model.Buy;

public class BuyPage {
	
	private int total;
	private int currentPage;
	private List<Buy> content;
	private int totalPages;

	public BuyPage(int total, int currentPage, List<Buy> content) {
		this.total = total;
		this.currentPage = currentPage;
		Collections.sort(content,
				(buy1, buy2) -> Integer.compare(buy1.getDate_No(), buy2.getDate_No()));
		this.content = content;
		
	}

	public int getToal() {
		return total;
	}

	public boolean hasNoCompanies() {
		return total == 0;
	}

	public boolean hasCompanies() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<Buy> getContent() {
		return content;
	}


}
