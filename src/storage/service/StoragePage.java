package storage.service;

import java.util.List;

import storage.model.Storage;

public class StoragePage {
	private int storageCode;
	private String storageName;
	private String storageAddress;
	private String storageUse;
	private List<Storage> storage;
	private int total;
	private int currentPage;
	private List<Storage> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public StoragePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoragePage(int storageCode, String storageName, String storageAddress, String storageUse,
			List<Storage> storage, int total, int currentPage, List<Storage> content, int totalPages, int startPage,
			int endPage) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.storageUse = storageUse;
		this.storage = storage;
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.totalPages = totalPages;
		this.startPage = startPage;
		this.endPage = endPage;
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

	public List<Storage> getStorage() {
		return storage;
	}

	public void setStorage(List<Storage> storage) {
		this.storage = storage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Storage> getContent() {
		return content;
	}

	public void setContent(List<Storage> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StoragePage(List<Storage> storage) {
		super();
		this.storage = storage;
	}

}
