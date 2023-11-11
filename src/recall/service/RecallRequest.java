package recall.service;

import java.util.Date;

public class RecallRequest {
	private Integer Recall_No;
	private String Member_Name;
	private String Storage_Name;
	private String Stock_Name;
	private int Unit_Price;
	private int Amount;
	private Date Process_Date;
	private String Process;

	public RecallRequest(Integer recall_No, String member_Name, String storage_Name, String stock_Name, int unit_Price,
			int amount, Date process_Date, String process) {
		super();
		Recall_No = recall_No;
		Member_Name = member_Name;
		Storage_Name = storage_Name;
		Stock_Name = stock_Name;
		Unit_Price = unit_Price;
		Amount = amount;
		Process_Date = process_Date;
		Process = process;
	}

	public Integer getRecall_No() {
		return Recall_No;
	}

	public void setRecall_No(Integer recall_No) {
		Recall_No = recall_No;
	}

	public String getMember_Name() {
		return Member_Name;
	}

	public void setMember_Name(String member_Name) {
		Member_Name = member_Name;
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

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public Date getProcess_Date() {
		return Process_Date;
	}

	public void setProcess_Date(Date process_Date) {
		Process_Date = process_Date;
	}

	public String getProcess() {
		return Process;
	}

	public void setProcess(String process) {
		Process = process;
	}
}
