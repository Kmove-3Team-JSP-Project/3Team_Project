<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="stock.model.Stock"%>
<%@ page import="stock.service.StockPage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫リスト</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<h1>
		<div align='center'>[ 在庫リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>
			<tr>
				<th>在庫コード</th>
				<th>数量</th>
				<th>倉庫名</th>
				<th>在庫名</th>
				<th>単価</th>
			</tr>
			<c:forEach items="${stockPage.content}" var="stock">
				<tr>
					<td>${stock.stock_Cord}</td>
					<td>${stock.amount}</td>
					<td>${stock.storage_Name}</td>
					<td>${stock.stock_Name}</td>
					<td>${stock.unit_Price}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br/>
	<br/>
	<br/>
	<div align='right'>
		<form action="stockSearch.do" method="post" target="_blank"
			onsubmit="return validateForm();">
			<label for="searchType">検索条件:</label> <select name="searchType"
				id="searchType">
				<option value="選択">選択</option>
				<option value="stock_name">在庫名</option>
				<option value="storage_name">倉庫名</option>
			</select> <label for="searchTerm">検索ワード:</label> <input type="text"
				name="searchTerm" id="searchTerm" placeholder="入力" required>
			<input type="submit" value="検索">
		</form>
	</div>
	<script>
		function validateForm() {
			var searchType = document.getElementById("searchType").value;
			var searchTerm = document.getElementById("searchTerm").value;

			if (searchType === "選択") {
				alert("検索条件を選択してください。");
				return false;
			}

			if (searchTerm.trim() === "") {
				alert("検索ワードを入力してください。");
				return false;
			}

			return true;
		}
	</script>
</body>
</html>