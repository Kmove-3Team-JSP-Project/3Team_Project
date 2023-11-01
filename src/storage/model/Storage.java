<<<<<<< Updated upstream
package storage.model;

public class Storage {

	private int storageCode;
	private String storageName;
	private String storageAddress;
	private String storageUse;

	public Storage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Storage(int storageCode, String storageName, String storageAddress, String storageUse) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.storageUse = storageUse;
	}

	public int getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public String getStorageUse() {
		return storageUse;
	}

	public void setStorageUse(String storageUse) {
		this.storageUse = storageUse;
	}

}
=======
package storage.model;

public class Storage {

	private int storageCode;
	private String storageName;
	private String storageAddress;
	private String storageUse;

	public int getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public String getStorageUse() {
		return storageUse;
	}

	public void setStorageUse(String storageUse) {
		this.storageUse = storageUse;
	}

	public Storage(int storageCode, String storageName, String storageAddress, String storageUse) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.storageUse = storageUse;
	}

	public Storage() {
		super();
		// TODO Auto-generated constructor stub
	}
}
>>>>>>> Stashed changes
