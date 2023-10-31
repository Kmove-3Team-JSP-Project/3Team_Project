package stock.model;

public class Stock {

	private int Stock_Cord;
	private int Amount;
	private String Storage_Name;
	private String Stock_Name;
	private int Unit_Price;

	public Stock(int stock_Cord, int amount, String storage_Name, String stock_Name, int unit_Price) {
		super();
		Stock_Cord = stock_Cord;
		Amount = amount;
		Storage_Name = storage_Name;
		Stock_Name = stock_Name;
		Unit_Price = unit_Price;
	}

	public int getStock_Cord() {
		return Stock_Cord;
	}

	public void setStock_Cord(int stock_Cord) {
		Stock_Cord = stock_Cord;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getStorage_Name() {
		return Storage_Name;
	}

	public void setStorage_Name(String storage_Name) {
		Storage_Name = storage_Name;
	}

	public String getStock_Name() {
		return Stock_Name;
	}

	public void setStock_Name(String stock_Name) {
		Stock_Name = stock_Name;
	}

	public int getUnit_Price() {
		return Unit_Price;
	}

	public void setUnit_Price(int unit_Price) {
		Unit_Price = unit_Price;
	}

}
