package storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import jdbc.JdbcUtil;

import storage.model.Storage;

public class StorageDao {

	public Storage selectById(Connection conn, int storageCode) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from storage where storage_id = ?");
			pstmt.setInt(1, storageCode);
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

	public List<Storage> readStorage(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select storage_id, storage_name, storage_address, use from Storage order by storage_id asc");
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
	public List<Storage> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from (select rownum as rnum, a.* "
				               + "from (select * from storage order by storage_id desc) a "
				               + "where rownum <=?) where rnum >=?");
			pstmt.setInt(1, startRow + size);
	         pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<Storage> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStorage(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Storage convertStorage(ResultSet rs) throws SQLException {
		return new Storage(rs.getInt("storageCode"), rs.getString("storageName"), rs.getString("storageAddress"), rs.getString("storageUse"));
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from storage");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public int autoCode(Connection conn) throws SQLException {
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn
				.prepareStatement("select storage_id from Storage where ROWNUM =1 ORDER BY storage_id desc")) {
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return 0;
		}
	}
}