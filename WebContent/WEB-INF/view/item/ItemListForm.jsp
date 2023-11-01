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
<title>品目リスト</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<h1>
		<div align='center'>[品目リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>
			<tr>
				<th>品目コード</th>
				<th>品目名</th>
				<th>単価</th>
				<th>品目区分</th>
			</tr>
			<c:forEach items="${itemPage.content}" var="item">
				<tr>
					<td>${item.item_Id}</td>
					<td>${item.item_Name}</td>
					<td>${item.unit_Price}</td>
					<td>${item.item_Class}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<div align='right'>
		<form action="ItemSearch.jsp" method="post">
			<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
				<option value="選択">選択</option>
				<option value="品目名">品目名</option>
				<option value="品目区分">品目区分</option>
			</select> <input type="text" name="keyword" placeholder="入力"> <input
				type="submit" value="検索">
		</form>
	</div>
</body>
</html>