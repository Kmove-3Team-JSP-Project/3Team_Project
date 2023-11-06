package sheet.model;

import java.util.Date;

public class Sheet {
	private Integer listNo;
	private String memberName;
	private String productName;
	private int unitPrice;
	private int amount;
	private int price;
	private String companyName;
	private String storageName;
	private Date listDate;
	private String process;
	
	public Sheet(Integer listNo, String memberName, String productName, int unitPrice, int amount, int price,
			String companyName, String storageName, Date listDate, String process) {
		this.listNo = listNo;
		this.memberName = memberName;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.price = price;
		this.companyName = companyName;
		this.storageName = storageName;
		this.listDate = listDate;
		this.process = process;
	}

	public Integer getListNo() {
		return listNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getProductName() {
		return productName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public int getPrice() {
		return price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getStorageName() {
		return storageName;
	}

	public Date getListDate() {
		return listDate;
	}

	public String getProcess() {
		return process;
	}
	
	
	
	
	
}
