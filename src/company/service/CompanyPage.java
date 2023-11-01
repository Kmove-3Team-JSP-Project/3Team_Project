package company.service;

import java.util.List;

import company.model.Company;

public class CompanyPage {

	private int total;
	private int currentPage;
	private List<Company> content;

	public CompanyPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyPage(int total, int currentPage, List<Company> content) {
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

	public List<Company> getContent() {
		return content;
	}

	public void setContent(List<Company> content) {
		this.content = content;
	}

}
