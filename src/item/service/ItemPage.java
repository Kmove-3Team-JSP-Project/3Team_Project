package item.service;

import java.util.List;

import item.model.Item;

public class ItemPage {
	private int total;
	private int currentPage;
	private List<Item> content;

	public ItemPage(int total, int currentPage, List<Item> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
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

	public List<Item> getContent() {
		return content;
	}

	public void setContent(List<Item> content) {
		this.content = content;
	}

}
