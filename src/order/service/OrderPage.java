package order.service;

import java.util.List;

import erp.model.Order;

public class OrderPage {
	private int total; // 전체 개시글 수
	private int currentPage; // 요청 페이지 번호
	private List<Order> content; // 화면에 출력할 게시글 목록
	private int totalPages; // 전체 페이지 개수
	private int startPage; // 페이지 이동 링크의 시작 번호
	private int endPage; // 끝 번호
	
	
	public OrderPage(int total, int currentPage, int size, List<Order> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPages = total / size;
			if(total % size > 0) {
				totalPages ++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0) startPage -= 5;
			
			endPage = startPage + 5;
			if(endPage > totalPages) endPage = totalPages;
		}
		
	}
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoOrders() {
		return total==0;
	}
	
	public boolean hasOrders() {
		return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	public List<Order> getContent() {
		return content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
}
