<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="company.model.Company"%>
<%@ page import="company.service.CompanyPage"%>
<html>
<head>
<meta charset="UTF-8">
<title>取引先検索</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<h1>
		<div align="center">[検索結果]</div>
	</h1>

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
			<c:choose>
				<c:when test="${empty companyPage.content}">
					<tr>
						<td colspan="6">検索結果がありません。</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${companyPage.content}" var="company">
						<tr>
							<td>${company.company_No}</td>
							<td>${company.company_Name}</td>
							<td>${company.master}</td>
							<td>${company.phone}</td>
							<td>${company.address}</td>
							<td>${company.myStorage}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div align="center">
		<a href="companyList.do">[取引先に戻る。]</a>
	</div>
</body>
</html>
