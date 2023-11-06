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
	

}
