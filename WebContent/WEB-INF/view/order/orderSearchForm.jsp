<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索窓</title>
<style type="text/css">
#wrap {
	min-height: calc(78.5vh - 30px);
	padding-bottom: 60px;
	text-align: center;
}

#my-div {
	margin-top: 50px;
	height: 50px;
	text-align: center;
	line-height: 50px;
	font-weight: bold;
	font-size: 30px;
}

table {
	margin-top: 10px;
	table-layout: fixed;
	margin-left: auto;
	margin-right: auto;
	width: 1100px;
	border: 1px solid #444444;
}

td {
	text-align: center;
	white-space: nowrap;
	border: 1px solid #444444;
}
</style>
</head>
<body>
	<div id="wrap">
		<form action="orderSearch.do">
		<h3 style="text-align: center;"> [発注要請]</h3>
			<table>
				<tr>
					<td>要請No</td>
					<td>担当者</td>
					<td>品目名</td>
					<td>単価</td>
					<td>数量</td>
					<td>金額</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>納期日</td>
					<td>進行状態</td>
				</tr>

				<c:if test="${orderPage.hasNoOrders()}">
					<tr>
						<td colspan="10">登録された発注要請が存在しません。</td>
					</tr>
				</c:if>
				<c:forEach var="order" items="${orderPage.content}">
					<tr>
						<td>${order.orderNo}</td>
						<td>${order.memberName}</td>
						<td>${order.itemName}</td>
						<td>${order.unitPrice}</td>
						<td>${order.amount}</td>
						<td>${order.price}</td>
						<td>${order.companyName}</td>
						<td>${order.storageName}</td>
						<td><fmt:formatDate value="${order.orderDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${order.progress}</td>
					</tr>
				</c:forEach>

				<c:if test="${orderPage.hasOrders()}">
					<tr>
						<td colspan="10"><c:if test="${orderPage.startPage > 5}">
								<a href="orderList.do?pageNo=${orderPage.startPage - 5}">[前のページ]</a>
							</c:if> <c:forEach var="pNo" begin="${orderPage.startPage}"
								end="${orderPage.endPage}">
								<a href="orderList.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach> <c:if test="${orderPage.endPage < orderPage.totalPages}">
								<a href="orderList.do?pageNo=${orderPage.startPage + 5}">[次のページ]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>