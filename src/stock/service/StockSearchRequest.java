package stock.service;

public class StockSearchRequest {
	private String searchType;
	private String searchTerm;

	public StockSearchRequest(String searchType, String searchTerm) {
		super();
		this.searchType = searchType;
		this.searchTerm = searchTerm;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

}
