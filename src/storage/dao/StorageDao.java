package storage.dao;


import java.beans.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;

import storage.model.Storage;
import ticket.model.Ticket;

public class StorageDao {

	public Storage selectById(Connection conn, String storageName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from storage where storageName = ?");
			pstmt.setString(1, storageName);
			rs = pstmt.executeQuery();
			Storage storage = null;

			if (rs.next()) {
				storage = new Storage(rs.getInt("storageCode"), rs.getString("storageName"),
						rs.getString("storageAddress"), rs.getString("storageUse"));
			}
			return storage;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	
	public void insert(Connection conn, Storage storage) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into member values (?,?,?,?)")) {
			pstmt.setInt(1, storage.getStorageCode());
			pstmt.setString(2, storage.getStorageName());
			pstmt.setString(3, storage.getStorageAddress());
			pstmt.setString(4, storage.getStorageUse());
			pstmt.executeUpdate();
		}
	}
	
	public List<Storage> readStorage(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select storage_id, storage_name, storage_address, use from Storage order by storage_id asc");
			List<Storage> result = new ArrayList<Storage>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Storage storage = new Storage();
				
				int storageCode = rs.getInt(1);
				String storageName = rs.getString(2);
				String storageAddress = rs.getString(3);
				String storageUse = rs.getString(4);
				
				storage.setStorageCode(storageCode);
				storage.setStorageName(storageName);
				storage.setStorageAddress(storageAddress);
				storage.setStorageUse(storageUse);
				result.add(storage);
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
}