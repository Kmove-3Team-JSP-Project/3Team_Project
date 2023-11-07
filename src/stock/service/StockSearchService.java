package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockSearchService {

	private StockDao stockDao = new StockDao(); // StockDao 인스턴스를 생성하여 StockSearchService 클래스 내에서 사용할 수 있도록 하는 필드

	private int size = 30; // 페이지당 표시할 Stock 레코드 수를 나타내는 필드

	// 주어진 StockSearchRequest와 페이지 번호를 사용하여 stock_name에 따라 Stock 레코드를 검색하고 결과를
	// StockPage 객체로 반환하는 메서드
	public StockPage searchStockByStock(StockSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 데이터베이스에서 stock_name에 해당하는 Stock 레코드의 총 수를 구합니다.
			int total = stockDao.getCountByStock(conn, searchRequest.getSearchTerm());
			// 데이터베이스에서 stock_name에 해당하는 Stock 레코드를 페이징하여 가져와 content 리스트에 저장합니다.
			List<Stock> content = stockDao.selectByStock(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			// StockPage 객체를 생성하여 검색 결과를 반환합니다.
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQLException이 발생하면 런타임 예외를 던져서 오류를 처리합니다.
			throw new RuntimeException("Failed to search Stocks by stock", e);
		}
	}

	// 주어진 StockSearchRequest와 페이지 번호를 사용하여 storage_name에 따라 Stock 레코드를 검색하고 결과를
	// StockPage 객체로 반환하는 메서드
	public StockPage searchStockByStorage(StockSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 데이터베이스에서 storage_name에 해당하는 Stock 레코드의 총 수를 구합니다.
			int total = stockDao.getCountByStorage(conn, searchRequest.getSearchTerm());
			// 데이터베이스에서 storage_name에 해당하는 Stock 레코드를 페이징하여 가져와 content 리스트에 저장합니다.
			List<Stock> content = stockDao.selectByStorage(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size,
					size);
			// StockPage 객체를 생성하여 검색 결과를 반환합니다.
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQLException이 발생하면 런타임 예외를 던져서 오류를 처리합니다.
			throw new RuntimeException("Failed to search Stocks by storage", e);
		}
	}
}
