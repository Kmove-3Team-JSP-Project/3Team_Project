package plan.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import plan.dao.PlanDao;
import plan.model.Plan;
import sheet.dao.SheetDao;
import sheet.model.Sheet;
import sheet.service.SheetRequest;

public class PlanRegisterService {

	private PlanDao planDao = new PlanDao();
	private SheetDao sheetDao = new SheetDao();
		

	public int getPlanNo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int planNo = planDao.getPlanNo(conn);
			conn.commit();
			return planNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			return -1;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Integer register(PlanRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Plan plan = toPlan(req);
			Plan savedPlan = planDao.insert(conn, plan);
			
			if (savedPlan == null) {
				throw new RuntimeException("fail to insert plan");
			}
			conn.commit();
			return savedPlan.getPlanNo();
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
	
	public Integer registerSheet(SheetRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Sheet sheet = toSheet(req);
			Sheet savedSheet = sheetDao.insert(conn, sheet);
			
			if (savedSheet == null) {
				throw new RuntimeException("fail to insert sheet");
			}
			conn.commit();
			return savedSheet.getListNo();
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
	
	private Sheet toSheet(SheetRequest req) {
		return new Sheet(req.getListNo(), req.getMemberName(), req.getProductName(), req.getUnitPrice(), req.getAmount(),
				req.getPrice(), req.getCompanyName(), req.getStorageName(), req.getListDate(), req.getProcess());
	}


	private Plan toPlan(PlanRequest req) {
		return new Plan(req.getPlanNo(), req.getMemberName(), req.getStockName(), req.getUnitPrice(), req.getAmount(),
				req.getPrice(), req.getCompanyName(), req.getStorageName(), req.getPlanDate(), req.getEnding());
	}

	public Map<String, Integer> getStockNamesWithUnitPrice() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            Map<String, Integer> itemDetails = planDao.getStockNamesWithUnitPrice(conn);
            conn.commit();
            return itemDetails;
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }


	public List<String> getCompanyList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> companyList = planDao.getCompanyList(conn);
			conn.commit();
			return companyList;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getStorageList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> storageList = planDao.getStorageList(conn);
			conn.commit();
			return storageList;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getAllStockNames() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			List<String> StockNames = planDao.getAllStockNames(conn);
			conn.commit();
			return StockNames;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	

}

