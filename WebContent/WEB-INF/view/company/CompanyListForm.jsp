<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="company.model.Company"%>
<%@ page import="company.service.CompanyPage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先リスト</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<h1 align="center">[取引先リスト]</h1>

	<div align="center">
		<table border="1" width="50%">
			<tr>
				<th>取引先コード</th>
				<th>取引先名</th>
				<th>担当者</th>
				<th>電話番号</th>
				<th>住所</th>
				<th>所有倉庫</th>
			</tr>
			<c:forEach var="company" items="${companyPage.content}">
				<tr>
					<td>${company.company_No}</td>
					<td>${company.company_Name}</td>
					<td>${company.master}</td>
					<td>${company.phone}</td>
					<td>${company.address}</td>
					<td>${company.myStorage}</td>
				</tr>
			</c:forEach>
		</table>

		<div align="center">
			<form action="companySearch.do" method="post" target="_blank"
				onsubmit="return vaildateForm();">
				<label for="searchType">検索条件:</label> <select name="searchType" id="searchType">
					<option value="選択">選択</option>
					<option value="company_Name">取引先名</option>
					<option value="master">担当者</option>
				</select> <input type="text"
				name="searchTerm" id="searchTerm" placeholder="入力" required> <input
					type="submit" value="検索"> 
					<input type="button" value="登録" onclick="window.location.href='companyRegister.do'" />
			</form>
		</div>
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
