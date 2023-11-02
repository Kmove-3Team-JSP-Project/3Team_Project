package storage.service;

import java.util.List;
import java.util.Map;

import storage.model.Storage;

public class StorageRequest {
	
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
	public StorageRequest(int storageCode, String storageName, String storageAddress, String storageUse,
			List<Storage> storage) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.storageUse = storageUse;
		this.storage = storage;
	}
	public StorageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, storageCode, "storage_id"); //대표적으로 아이디 값이 비어있지 않으면 에러 값이 빔.
		checkEmpty(errors, storageName, "storage_name");
		checkEmpty(errors, storageAddress, "storage_address");
		checkEmpty(errors, storageUse, "use");

	}
	
	//회원가입시 값이 비었는지 확인하는 메서드 이 메서드를 이용해 위의 메서드를 만듬.
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
	private void checkEmpty(Map<String, Boolean> errors,  value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
