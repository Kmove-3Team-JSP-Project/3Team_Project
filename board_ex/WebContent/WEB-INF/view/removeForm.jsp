<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>

<body>
	글을 삭제하시겠습니까?
	<form action="remove.do" method="post">
		<p>
			번호:<br />${remReq.articleNumber}
		</p>

		<input type="submit" value="예"> <input type="hidden" name="no"
			value="${remReq.articleNumber}">
	</form>
	
	<br> ${ctxPath = pageContext.request.contextPath ; ''}
	<a href="${ctxPath}/article/read.do?no=${remReq.articleNumber}">[아니요]</a>
</body>
</html>