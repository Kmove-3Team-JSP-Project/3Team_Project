package item.service;

import java.util.Collections;
import java.util.List;

import item.model.Item;

public class ItemPage {
	private int total;
	private int currentPage;
	private List<Item> content;

	public ItemPage(int total, int currentPage, List<Item> content) {
	    this.total = total;
	    this.currentPage = currentPage;
	    Collections.sort(content, (item1, item2) -> Integer.compare(item1.getItem_Id(), item2.getItem_Id()));
	    this.content = content;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Item> getContent() {
		return content;
	}
}