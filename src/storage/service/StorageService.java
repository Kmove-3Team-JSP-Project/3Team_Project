package storage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import jdbc.connection.ConnectionProvider;
import storage.dao.StorageDao;
import storage.model.Storage;


public class StorageService {

	private StorageDao storageDao = new StorageDao();
	private int size = 10;
	
	public StoragePage getStoragePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = storageDao.selectCount(conn);
			List<Storage> content = storageDao.select(conn, (pageNum-1) * size, size);
			return new StoragePage(total, pageNum, size, content);
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
