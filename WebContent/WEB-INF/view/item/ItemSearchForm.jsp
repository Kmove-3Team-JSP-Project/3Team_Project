<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="item.model.Item"%>
<%@ page import="item.service.ItemPage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>品目検索結果</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<h1>
		<div align='center'>[品目検索結果]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>
			<tr>
				<th>品目コード</th>
				<th>品目名</th>
				<th>単価</th>
				<th>品目区分</th>
			</tr>
			<c:choose>
				<c:when test="${empty itemPage.content}">
					<tr>
						<td colspan="4">検索結果がありません。</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${itemPage.content}" var="item">
						<tr>
							<td>${item.item_Id}</td>
							<td>${item.item_Name}</td>
							<td>${item.unit_Price}</td>
							<td>${item.item_Class}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div align='center'>
		<a href="ItemListForm.jsp">品目リストに戻る</a>
	</div>
</body>
</html>