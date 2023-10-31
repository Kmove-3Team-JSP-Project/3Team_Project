package recall.model;

public class Recall {

	private int Recall_No;
	private String Process;
	private String Stock_Name;
	private int Amount;
	private String Storage_Name;

	public Recall(int recall_No, String process, String stock_Name, int amount, String storage_Name) {
		super();
		Recall_No = recall_No;
		Process = process;
		Stock_Name = stock_Name;
		Amount = amount;
		Storage_Name = storage_Name;
	}

	public int getRecall_No() {
		return Recall_No;
	}

	public void setRecall_No(int recall_No) {
		Recall_No = recall_No;
	}

	public String getProcess() {
		return Process;
	}

	public void setProcess(String process) {
		Process = process;
	}

	public String getStock_Name() {
		return Stock_Name;
	}

	public void setStock_Name(String stock_Name) {
		Stock_Name = stock_Name;
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

}
