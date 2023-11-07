package stock.service;

import java.util.Collections;
import java.util.List;

import stock.model.Stock;

public class StockPage {

	private int total; // StockPage에 포함된 총 Stock 레코드의 수를 나타내는 필드

	private int currentPage; // 현재 페이지 번호를 나타내는 필드

	private List<Stock> content; // 현재 페이지에 표시할 Stock 객체 목록을 저장하는 필드

	// StockPage 클래스의 생성자, total, currentPage, content를 초기화하고 content를 Stock_Cord로
	// 오름차순 정렬합니다.
	public StockPage(int total, int currentPage, List<Stock> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		// Stock 객체를 Stock_Cord로 오름차순 정렬하는 Comparator를 사용하여 content를 정렬합니다.
		Collections.sort(content, (stock1, stock2) -> Integer.compare(stock1.getStock_Cord(), stock2.getStock_Cord()));
		this.content = content;
	}

	// total 필드의 값을 반환하는 메서드
	public int getTotal() {
		return total;
	}

	// total 필드의 값을 설정하는 메서드
	public void setTotal(int total) {
		this.total = total;
	}

	// currentPage 필드의 값을 반환하는 메서드
	public int getCurrentPage() {
		return currentPage;
	}

	// currentPage 필드의 값을 설정하는 메서드
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// content 필드의 값을 반환하는 메서드
	public List<Stock> getContent() {
		return content;
	}

	// content 필드의 값을 설정하는 메서드
	public void setContent(List<Stock> content) {
		this.content = content;
	}
}
