package storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

import storage.model.Storage;

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

	// member 테이블에 데이터 추가
	public void insert(Connection conn, Storage storage) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into member values (?,?,?,?)")) {
			pstmt.setInt(1, storage.getStorageCode());
			pstmt.setString(2, storage.getStorageName());
			pstmt.setString(3, storage.getStorageAddress());
			pstmt.setString(4, storage.getStorageUse());
			pstmt.executeUpdate();
		}
	}

}
