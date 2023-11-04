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
<title>在庫検索結果</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<h1>
		<div align='center'>[在庫検索結果]</div>
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
			<c:choose>
				<c:when test="${empty stockPage.content}">
					<tr>
						<td colspan="5">検索結果がありません。</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${stockPage.content}" var="stock">
						<tr>
							<td>${stock.stock_Cord}</td>
							<td>${stock.amount}</td>
							<td>${stock.storage_Name}</td>
							<td>${stock.stock_Name}</td>
							<td>${stock.unit_Price}</td>
							<td>
								<form action="recallRegister.do" method="post">
									<input type="hidden" name="stockCord"
										value="${stock.stock_Cord}"> <input type="submit"
										value="修整">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div align='center'>
		<a href="RecallListForm.jsp">後ろに戻る</a>
	</div>
</body>
</html>