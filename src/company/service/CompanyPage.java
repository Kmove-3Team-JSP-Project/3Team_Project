package company.service;

import java.util.Collections;
import java.util.List;

import company.model.Company;

public class CompanyPage {

	private int total;
	private int currentPage;
	private List<Company> content;
	private int totalPages;

	public CompanyPage(int total, int currentPage, List<Company> content) {
		this.total = total;
		this.currentPage = currentPage;
		Collections.sort(content,
				(company1, company2) -> Integer.compare(company1.getCompany_No(), company2.getCompany_No()));
		this.content = content;
	}

	public CompanyPage() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public boolean hasNoCompany() {
		return total == 0;
	}

	public boolean hasCompany() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<Company> getContent() {
		return content;
	}

}