package item.model;

public class Item {

	private int Item_Id;
	private String Item_Name;
	private int Unit_Price;
	private String Item_Class;

	public Item(int item_Id, String item_Name, int unit_Price, String item_Class) {
		super();
		Item_Id = item_Id;
		Item_Name = item_Name;
		Unit_Price = unit_Price;
		Item_Class = item_Class;
	}

	public int getItem_Id() {
		return Item_Id;
	}

	public void setItem_Id(int item_Id) {
		Item_Id = item_Id;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public int getUnit_Price() {
		return Unit_Price;
	}

	public void setUnit_Price(int unit_Price) {
		Unit_Price = unit_Price;
	}

	public String getItem_Class() {
		return Item_Class;
	}

	public void setItem_Class(String item_Class) {
		Item_Class = item_Class;
	}

}
