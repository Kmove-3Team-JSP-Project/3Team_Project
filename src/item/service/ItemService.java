package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemService {

	// ItemDao 인스턴스를 생성하고 필드로 유지
	private ItemDao itemDao = new ItemDao();

	// 페이지당 항목 수를 지정
	private int size = 30;

	// 페이지 번호를 기반으로 상품 페이지를 반환하는 메서드
	public ItemPage getItemPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 전체 항목 수를 데이터베이스에서 조회
			int total = itemDao.getCount(conn);

			// 현재 페이지의 항목 목록을 데이터베이스에서 조회
			List<Item> content = itemDao.select(conn, (pageNum - 1) * size, size);

			// 항목 목록을 항목 ID를 기준으로 정렬
			content.sort((item1, item2) -> Integer.compare(item1.getItem_Id(), item2.getItem_Id()));

			// 결과 페이지 객체를 생성하여 반환
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQL 예외 발생 시 런타임 예외로 변환
			throw new RuntimeException("Failed to get ItemPage", e);
		}
	}
}