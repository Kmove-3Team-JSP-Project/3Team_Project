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
					<td>${company.company_no}</td>
					<td>${company.company_name}</td>
					<td>${company.master}</td>
					<td>${company.phone}</td>
					<td>${company.address}</td>
					<td>${company.myStroage}</td>
				</tr>
			</c:forEach>
		</table>

		<div align="center">
			<form action="CompanySearchForm.jsp" method="post">
				<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
					<option value="company_no">取引先コード</option>
					<option value="company_name">取引先名</option>
					<option value="master">担当者</option>
					<option value="phone">電話番号</option>
					<option value="myStroage">所有倉庫</option>
				</select> <input type="text" name="keyword" placeholder="入力"> <input
					type="submit" value="検索"> <input type="button" value="登録"
					onclick="window.location.href='CompanyRegisterForm.jsp'" />
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
