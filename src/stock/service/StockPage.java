package stock.service;

import java.util.Collections;
import java.util.List;

import stock.model.Stock;

public class StockPage {
	private int total;
	private int currentPage;
	private List<Stock> content;

	public StockPage(int total, int currentPage, List<Stock> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		Collections.sort(content, (stock1, stock2) -> Integer.compare(stock1.getStock_Cord(), stock2.getStock_Cord()));
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

	public List<Stock> getContent() {
		return content;
	}

	public void setContent(List<Stock> content) {
		this.content = content;
	}

}
