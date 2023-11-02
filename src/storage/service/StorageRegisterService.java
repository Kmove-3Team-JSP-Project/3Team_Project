package storage.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.service.DuplicateIdException;
import storage.dao.StorageDao;
import storage.model.Storage;
import sun.security.krb5.internal.Ticket;

public class StorageRegisterService {

	private StorageDao storageDao = new StorageDao();
	
	public void storageRegister(StorageRequest storageReq) {
		Connection conn = null;
		try {
			// 트랜젝션을 위해 오토커밋을 false로 둠.
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			// 아이디 중복확인
			Storage storage = StorageDao.selectById(conn, storageReq.getStorageCode());
			if (storage != null) { // member에 들어간 값이 있다 => 같은 아이디 값이 있다.
				JdbcUtil.rollback(conn); // 롤백
				throw new DuplicateIdException(); // 오류 발생.
			}
			// 중복된 값이 없다면 값을 DB에 저장.
			ticketDao.insert(conn, new Ticket(ticketReq.getTicketNo(), ticketReq.getCarNo(), ticketReq.getPhone(),
					ticketReq.getGrade(), ticketReq.getTicketStat(), ticketReq.getStartDate(), ticketReq.getEndDate()));
			conn.commit(); // 커밋함.
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int getAutonum() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int ticketNo = ticketDao.autoNo(conn);
			ticketNo = ticketNo + 1;
			return ticketNo;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
}
