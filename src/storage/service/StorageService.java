package storage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import storage.dao.StorageDao;
import storage.model.Storage;


public class StorageService {

	private StorageDao storageDao = new StorageDao();

	public StorageRequest getReadStorage() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			List<Storage> storage = storageDao.readStorage(conn);
			return new StorageRequest(storage);
		} catch (SQLException e) {
			throw new RuntimeException();
			// TODO: handle exception
		}
	}

}
