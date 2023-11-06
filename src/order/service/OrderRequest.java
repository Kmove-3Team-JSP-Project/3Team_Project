package order.service;

import java.util.Date;
import java.util.Map;

public class OrderRequest {
	
	private Integer orderNo;
	private String memberName;
	private String itemName;
	private int unitPrice;
	private int amount;
	private int price;
	private String companyName;
	private String storageName;
	private Date orderDate;
	private String progress;
	
	
	public OrderRequest(Integer orderNo, String memberName, String itemName, int unitPrice, int amount, int price,
			String companyName, String storageName, Date orderDate, String progress) {
		this.orderNo = orderNo;
		this.memberName = memberName;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.price = price;
		this.companyName = companyName;
		this.storageName = storageName;
		this.orderDate = orderDate;
		this.progress = progress;
	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public String getItemName() {
		return itemName;
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


	public Date getOrderDate() {
		return orderDate;
	}


	public String getProgress() {
		return progress;
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, memberName, "memberName");
		checkEmpty(errors, itemName, "itemName");
		checkEmpty(errors, companyName, "companyName");
		checkEmpty(errors, storageName, "storageName");
		checkEmpty(errors, progress, "progress");
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

}
