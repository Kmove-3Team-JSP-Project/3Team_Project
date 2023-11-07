package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;

public class ItemSearchService {

	// ItemDao 인스턴스를 생성하고 필드로 유지
	private ItemDao itemDao = new ItemDao();

	// 페이지당 항목 수를 지정
	private int size = 30;

	// 상품 이름으로 검색하여 결과 페이지를 반환하는 메서드
	public ItemPage searchItemsByName(ItemSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 검색어에 해당하는 전체 항목 수를 조회
			int total = itemDao.getCountByName(conn, searchRequest.getSearchTerm());

			// 현재 페이지의 항목 목록을 조회
			List<Item> content = itemDao.selectByName(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size, size);

			// 결과 페이지 객체를 생성하여 반환
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQL 예외 발생 시 런타임 예외로 변환
			throw new RuntimeException("Failed to search items by name", e);
		}
	}

	// 상품 클래스로 검색하여 결과 페이지를 반환하는 메서드
	public ItemPage searchItemsByClass(ItemSearchRequest searchRequest, int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 검색어에 해당하는 전체 항목 수를 조회
			int total = itemDao.getCountByClass(conn, searchRequest.getSearchTerm());

			// 현재 페이지의 항목 목록을 조회
			List<Item> content = itemDao.selectByClass(conn, searchRequest.getSearchTerm(), (pageNum - 1) * size, size);

			// 결과 페이지 객체를 생성하여 반환
			return new ItemPage(total, pageNum, content);
		} catch (SQLException e) {
			// SQL 예외 발생 시 런타임 예외로 변환
			throw new RuntimeException("Failed to search items by class", e);
		}
	}
}
