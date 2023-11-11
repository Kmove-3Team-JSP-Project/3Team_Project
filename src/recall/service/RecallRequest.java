package recall.service;

import java.util.Date;
import java.util.Map;

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

	public String getMember_Name() {
		return Member_Name;
	}

	public String getStorage_Name() {
		return Storage_Name;
	}

	public String getStock_Name() {
		return Stock_Name;
	}

	public int getUnit_Price() {
		return Unit_Price;
	}

	public int getAmount() {
		return Amount;
	}

	public Date getProcess_Date() {
		return Process_Date;
	}

	public String getProcess() {
		return Process;
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, Member_Name, "member_Name");
		checkEmpty(errors, Storage_Name, "Storage_Name");
		checkEmpty(errors, Stock_Name, "Stock_Name");
		checkEmpty(errors, Process, "Process");
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

}
