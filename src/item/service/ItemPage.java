package item.service;

import java.util.Collections;
import java.util.List;

import item.model.Item;

public class ItemPage {

	// 전체 항목 수
	private int total;

	// 현재 페이지 번호
	private int currentPage;

	// 현재 페이지에 표시할 항목 목록
	private List<Item> content;

	public ItemPage(int total, int currentPage, List<Item> content) {
		this.total = total;
		this.currentPage = currentPage;
		// 항목을 항목 ID를 기준으로 정렬
		Collections.sort(content, (item1, item2) -> Integer.compare(item1.getItem_Id(), item2.getItem_Id()));
		this.content = content;
	}

	// 전체 항목 수를 반환하는 메서드
	public int getTotal() {
		return total;
	}

	// 현재 페이지 번호를 반환하는 메서드
	public int getCurrentPage() {
		return currentPage;
	}

	// 현재 페이지의 항목 목록을 반환하는 메서드
	public List<Item> getContent() {
		return content;
	}
}