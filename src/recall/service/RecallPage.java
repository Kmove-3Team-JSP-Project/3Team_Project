package recall.service;

import java.util.Collections;
import java.util.List;

import recall.model.Recall;

public class RecallPage {
	private int total; // 전체 Recall 레코드 수를 나타내는 필드
	private int currentPage; // 현재 페이지 번호를 나타내는 필드
	private List<Recall> content; // Recall 객체의 리스트를 나타내는 필드

	// RecallPage 객체를 초기화하는 생성자
	public RecallPage(int total, int currentPage, List<Recall> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		// Recall 객체 리스트를 Recall_No를 기준으로 정렬하여 설정
		Collections.sort(content,
				(recall1, recall2) -> Integer.compare(recall1.getRecall_No(), recall2.getRecall_No()));
		this.content = content;
	}

	// 전체 Recall 레코드 수를 반환하는 메서드
	public int getTotal() {
		return total;
	}

	// 전체 Recall 레코드 수를 설정하는 메서드
	public void setTotal(int total) {
		this.total = total;
	}

	// 현재 페이지 번호를 반환하는 메서드
	public int getCurrentPage() {
		return currentPage;
	}

	// 현재 페이지 번호를 설정하는 메서드
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// Recall 객체 리스트를 반환하는 메서드
	public List<Recall> getContent() {
		return content;
	}

	// Recall 객체 리스트를 설정하는 메서드
	public void setContent(List<Recall> content) {
		// Recall 객체 리스트를 Recall_No를 기준으로 정렬하여 설정
		Collections.sort(content,
				(recall1, recall2) -> Integer.compare(recall1.getRecall_No(), recall2.getRecall_No()));
		this.content = content;
	}
}
