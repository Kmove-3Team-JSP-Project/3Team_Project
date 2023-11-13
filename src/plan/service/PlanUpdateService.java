package plan.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import plan.dao.PlanDao;
import sheet.dao.SheetDao;

public class PlanUpdateService {
	private PlanDao planDao = new PlanDao();
	private SheetDao sheetDao = new SheetDao();
	
	public Integer updatePlanEnding(int planNo, String ending) {
		// TODO Auto-generated method stub
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedPlan = planDao.update(conn, planNo, ending);
			
			if (savedPlan < 0) {
				throw new RuntimeException("fail to update plan");
			}
			conn.commit();
			return savedPlan;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Integer updateSheetEnding(int planNo, String ending) {
		// TODO Auto-generated method stub
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedPlan = sheetDao.update(conn, planNo, ending);
			
			if (savedPlan < 0) {
				throw new RuntimeException("fail to update sheet");
			}
			conn.commit();
			return savedPlan;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public boolean stockCompleted(int planNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int savedOrder = planDao.updateStockFromCompletedOrders(conn, planNo);
			if (savedOrder < 0) {
				return false;
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
