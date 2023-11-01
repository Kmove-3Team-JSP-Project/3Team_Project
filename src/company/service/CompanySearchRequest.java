package company.service;

import java.util.Map;

public class CompanySearchRequest {

	private int company_No;
	private String company_Name;
	private String master;
	private String phone;
	private String address;
	private String myStorage;

	public CompanySearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanySearchRequest(int company_No, String company_Name, String master, String phone, String address,
			String myStorage) {
		super();
		this.company_No = company_No;
		this.company_Name = company_Name;
		this.master = master;
		this.phone = phone;
		this.address = address;
		this.myStorage = myStorage;
	}

	public int getCompany_No() {
		return company_No;
	}

	public void setCompany_No(int company_No) {
		this.company_No = company_No;
	}

	public String getCompany_Name() {
		return company_Name;
	}

	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMyStorage() {
		return myStorage;
	}

	public void setMyStorage(String myStorage) {
		this.myStorage = myStorage;
	}

	public void validate(Map<String, Boolean> errors) {
		if (company_Name == null || company_Name.trim().isEmpty()) {
			errors.put("companyName", Boolean.TRUE);
		}
	}
}
