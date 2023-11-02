package storage.service;

import java.util.List;

import storage.model.Storage;

public class StoragePage {
	private int storageCode;
	private String storageName;
	private String storageAddress;
	private String storageUse;
	private List<Storage> storage;

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

	public List<Storage> getStorage() {
		return storage;
	}

	public void setStorage(List<Storage> storage) {
		this.storage = storage;
	}

	public StoragePage(int storageCode, String storageName, String storageAddress, String storageUse,
			List<Storage> storage) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.storageUse = storageUse;
		this.storage = storage;
	}

	public StoragePage(List<Storage> storage) {
		super();
		this.storage = storage;
	}

	public StoragePage() {
		super();
		// TODO Auto-generated constructor stub
	}
}
