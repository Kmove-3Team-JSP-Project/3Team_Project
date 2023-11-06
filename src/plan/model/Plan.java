package plan.model;

import java.util.Date;

public class Plan {

	private Integer planNo;
	private String memberName;
	private String stockName;
	private int unitPrice;
	private int amount;
	private int price;
	private String companyName;
	private String storageName;
	private Date planDate;
	private String Ending;
	
	public Plan(Integer planNo, String memberName, String stockName, int unitPrice, int amount, int price,
			String companyName, String storageName, Date planDate, String ending) {
		this.planNo = planNo;
		this.memberName = memberName;
		this.stockName = stockName;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.price = price;
		this.companyName = companyName;
		this.storageName = storageName;
		this.planDate = planDate;
		Ending = ending;
	}

	public Integer getPlanNo() {
		return planNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getStockName() {
		return stockName;
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

	public Date getPlanDate() {
		return planDate;
	}

	public String getEnding() {
		return Ending;
	}
	
	

}
