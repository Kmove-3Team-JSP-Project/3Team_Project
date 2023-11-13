package sheet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import sheet.dao.SheetDao;
import sheet.model.Sheet;

public class SheetListService {
	private SheetDao sheetDao = new SheetDao();
	private int size = 10;
	
	public SheetPage getSheetPage(int pageNum){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = sheetDao.selectCount(conn);
			List<Sheet> content = sheetDao.select(conn, (pageNum-1) * size, size);
			return new SheetPage(total, pageNum, size, content);		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SheetPage getSheetSearchPage(String searchField, String searchText, int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = sheetDao.selectCountSearch(conn, searchField, searchText);
			List<Sheet> content = sheetDao.getSearch(conn, searchField, searchText, (pageNum-1) * size, size);
			return new SheetPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
