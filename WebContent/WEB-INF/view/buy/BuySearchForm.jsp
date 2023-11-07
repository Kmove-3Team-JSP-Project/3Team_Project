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
<title>購買リスト検索結果</title>
</head>
<body>
	<h1>
		<%@ include file="/header.jsp"%>
		<div align="center">[検索結果]</div>
	</h1>
	&nbsp;&nbsp;&nbsp;

	<h2>
		<div align="center">[検索語:担当者]</div>
	</h2>

	<div align="center">
		<table border="1" width="50%">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<tr>
				<th>取引先名</th>
				<th>品目名</th>
				<th>担当者</th>
			</tr>
			<c:choose>
				<c:when test="${empty buyPage.content}">
					<tr>
						<td colspan="3">検索結果がありません。</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${buyPage.content}" var="buy">
						<tr>
							<td>${buy.company_name}</td>
							<td>${buy.item_name}</td>
							<td>${buy.member_name}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div align="center">
		<a href="buyList.do">[購買リストに戻る。]</a>
	</div>
</body>
</html>
