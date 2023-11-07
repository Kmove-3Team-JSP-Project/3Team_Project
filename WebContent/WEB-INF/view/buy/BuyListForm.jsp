<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="buy.model.Buy"%>
<%@ page import="buy.service.BuyPage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購買リスト</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<h1 align="center">[購買リスト]</h1>
	<div align="center">
		<table border="1" width="50%">
			<tr>
				<th>日付No</th>
				<th>取引先名</th>
				<th>数量</th>
				<th>品目名</th>
				<th>単価</th>
				<th>購買日付</th>
				<th>担当者</th>
			</tr>
			<c:forEach var="buy" items="${buyPage.content}">
				<tr>
					<td>${buy.date_no}</td>
					<td>${buy.company_name}</td>
					<td>${buy.amount}</td>
					<td>${buy.item_name}</td>
					<td>${buy.unit_price}</td>
					<td>${buy.buy_date}</td>
					<td>${buy.member_name}</td>
				</tr>
			</c:forEach>
		</table>
		<div align="center">
			<form action="buySearch.do" method="post" target="_blank"
				onsubmit="return validateForm();">
				<label for="検索条件">検索条件:</label> <select name="searchType" id="検索条件">
					<option value="company_name">取引先名</option>
					<option value="item_name">品目名</option>
					<option value="member_name">担当者</option>
				</select> <input type="text" name="searchTerm" id="searchTerm"
					placeholder="入力"> <input type="submit" value="検索">
				<input type="button" value="購買要請"
					onclick="window.location.href='BuyRegisterForm.jsp'" />
			</form>
		</div>
	</div>
	<script>
		function validateForm() {
			var searchType = document.getElementById("検索条件").value;
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
