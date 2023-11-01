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
			<c:forEach items="${searchResult.content}" var="item">
				<tr>
					<td>${item.item_Id}</td>
					<td>${item.item_Name}</td>
					<td>${item.unit_Price}</td>
					<td>${item.item_Class}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align='center'>
		<a href="ItemListForm.jsp">品目リストに戻る</a>
	</div>
</body>
</html>
