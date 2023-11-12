package plan.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import plan.dao.PlanDao;
import plan.model.Plan;

public class PlanListService {
	private PlanDao planDao = new PlanDao();
	private int size = 10;
	
	public PlanPage getPlanPage(int pageNum){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = planDao.selectCount(conn);
			List<Plan> content = planDao.select(conn, (pageNum-1) * size, size);
			return new PlanPage(total, pageNum, size, content);		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PlanPage getPlanCheckPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = planDao.selectCountEnding(conn);
			List<Plan> content = planDao.selectEnding(conn, (pageNum-1) * size, size);
			return new PlanPage(total, pageNum, size, content);		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PlanPage getPlanSearchPage(String searchField, String searchText, int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = planDao.selectCountSearch(conn, searchField, searchText);
			List<Plan> content = planDao.getSearch(conn, searchField, searchText, (pageNum-1) * size, size);
			return new PlanPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
