package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

public class StockService {

	private StockDao stockDao = new StockDao(); // StockDao 인스턴스를 생성하여 StockService 클래스 내에서 사용할 수 있도록 하는 필드

	private int size = 30; // 페이지당 표시할 Stock 레코드 수를 나타내는 필드

	// 주어진 페이지 번호를 사용하여 모든 Stock 레코드를 페이지별로 가져와 StockPage 객체로 반환하는 메서드
	public StockPage getStockPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 데이터베이스에서 모든 Stock 레코드의 총 수를 구합니다.
			int total = stockDao.getCount(conn);
			// 데이터베이스에서 모든 Stock 레코드를 페이징하여 가져와 content 리스트에 저장합니다.
			List<Stock> content = stockDao.select(conn, (pageNum - 1) * size, size);
			// content 리스트를 Stock_Cord로 오름차순 정렬합니다.
			content.sort((stock1, stock2) -> Integer.compare(stock1.getStock_Cord(), stock2.getStock_Cord()));
			// StockPage 객체를 생성하여 페이지별 Stock 레코드 목록을 반환합니다.
			return new StockPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQLException이 발생하면 런타임 예외를 던져서 오류를 처리합니다.
			throw new RuntimeException("Failed to get StockPage. Cause: " + e.getMessage(), e);
		}
	}
}
