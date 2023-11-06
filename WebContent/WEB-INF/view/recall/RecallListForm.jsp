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
<title>不良処理要請リスト</title>
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
		<div align='center'>[ 不良処理要請リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>
			<tr>
				<th>不良コード</th>
				<th>担当者</th>
				<th>倉庫名</th>
				<th>在庫名</th>
				<th>数量</th>
				<th>処理日付</th>
				<th>処理方法</th>
			</tr>
			<c:forEach items="${recallPage.content}" var="recall">
				<tr>
					<td>${recall.recall_No}</td>
					<td>${recall.member_Name}</td>
					<td>${recall.storage_Name}</td>
					<td>${recall.stock_Name}</td>
					<td>${recall.amount}</td>
					<td>${recall.process_Date}</td>
					<td>${recall.process}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<br />
	<br />
	<div align='center'>
		<h2>在庫検索</h2>
		<form action="recallList.do" method="post" target="_blank"
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