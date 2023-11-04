package buy.model;

public class Buy {

	private int date_No;
	private int unit_Price;
	private String company_Name;
	private String item_Name;
	private int price;
	private int amount;
	private int buy_date;
	private String member_Name;

	public Buy() {
		super();
	}

	public Buy(int date_No, int unit_Price, String company_Name, String item_Name, int price, int amount, int buy_date,
			String member_Name) {
		super();
		this.date_No = date_No;
		this.unit_Price = unit_Price;
		this.company_Name = company_Name;
		this.item_Name = item_Name;
		this.price = price;
		this.amount = amount;
		this.buy_date = buy_date;
		this.member_Name = member_Name;
	}

	public int getDate_No() {
		return date_No;
	}

	public void setDate_No(int date_No) {
		this.date_No = date_No;
	}

	public int getUnit_Price() {
		return unit_Price;
	}

	public void setUnit_Price(int unit_Price) {
		this.unit_Price = unit_Price;
	}

	public String getCompany_Name() {
		return company_Name;
	}

	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}

	public String getItem_Name() {
		return item_Name;
	}

	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(int buy_date) {
		this.buy_date = buy_date;
	}

	public String getMember_Name() {
		return member_Name;
	}

	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}

}
