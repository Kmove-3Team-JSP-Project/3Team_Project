package company.service;

public class CompanyRequest {

	private int company_No;
	private String company_Name;
	private String name;
	private String phone;
	private String address;
	private String myStorage;

	public CompanyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyRequest(int company_No, String company_Name, String name, String phone, String address,
			String myStorage) {
		super();
		this.company_No = company_No;
		this.company_Name = company_Name;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
