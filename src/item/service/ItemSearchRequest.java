package item.service;


	public class ItemSearchRequest {
	    private String searchType;
	    private String searchTerm;

	    public ItemSearchRequest(String searchType, String searchTerm) {
	        this.searchType = searchType;
	        this.searchTerm = searchTerm;
	    }

	    public String getSearchType() {
	        return searchType;
	    }

	    public String getSearchTerm() {
	        return searchTerm;
	    }
	}

