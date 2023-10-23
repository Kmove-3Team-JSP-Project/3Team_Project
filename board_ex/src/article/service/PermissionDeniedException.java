package article.service;

// 게시글 수정권한이 없는(작성자가 아닌) 경우 예외처리
public class PermissionDeniedException extends RuntimeException {

}
