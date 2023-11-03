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
	<%@ include file="/header.jsp"%>

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
	<br />
	<br />
	<br />
	<div align='right'>
		<form action="itemSearch.do" method="post" target="_blank"
			onsubmit="return validateForm();">
			<label for="searchType">検索条件:</label> <select name="searchType"
				id="searchType">
				<option value="選択">選択</option>
				<option value="item_name">品目名</option>
				<option value="item_class">品目区分</option>
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