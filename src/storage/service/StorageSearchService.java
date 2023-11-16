package storage.service;

import java.sql.Connection;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import storage.dao.StorageDao;
import storage.model.Storage;

public class StorageSearchService {
	private StorageDao storageDao = new StorageDao();

    public List<Storage> getAllStorage() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            List<Storage> storageList = storageDao.readStorage(conn);
            return storageList;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception or throw a custom exception
            return null;
        } finally {
            JdbcUtil.close(conn);
        }
    }
}
