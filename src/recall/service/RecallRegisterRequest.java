package recall.service;

public class RecallRegisterRequest {

	private int Stock_Cord;
	private int Amount;

	public RecallRegisterRequest(int stock_Cord, int amount) {
		super();
		Stock_Cord = stock_Cord;
		Amount = amount;
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

}
