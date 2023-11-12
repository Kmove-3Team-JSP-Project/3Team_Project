<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注要請</title>
<style>
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
<%@ include file="/header.jsp"%>
<body>
	<div id="wrap">
		<form action="orderList.do">

			<h1 style="text-align: center;">
				[発注要請] <input type="button" value="進行変更"
					style="font-size: 15px; width: 80px; height: 30px; position: absolute; right: 225px; top: 100px;"
					onclick="window.open('orderCheck.do', '発注要請検索', 'width=1200, height=700')" />
			</h1>
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

		<form name="Data">
			<select name="condition"
				style="margin-top: 20px; margin-left: 710px; font-size: 15px;">
				<option value="orderNo">要請No</option>
				<option value="memberName">担当者</option>
				<option value="itemName">品目名</option>
				<option value="companyName">取引先名</option>
				<option value="storageName">倉庫名</option>
				<option value="orderDate">納期日</option>
				<option value="progress">進行状態</option>
			</select> <input type="text" placeholder="検索ワード入力" name="detail"
				maxlength="50" style="margin-top: 20px; font-size: 15px;"> <input
				type="button" value="検索"
				style="font-size: 15px; width: 50px; height: 30px; margin-top: 20px;"
				onclick="Check(this.form);" /> <input type="button" value="登録"
				style="font-size: 15px; width: 50px; height: 30px; margin-top: 20px;"
				onclick="window.open('orderRegister.do', '発注要請登録', 'width=700, height=700')" />
		</form>
	</div>

</body>
<script type="text/javascript">
	function Check(form) {
		var condition = form.condition.value;
		var detail = form.detail.value;
		if (detail === '') {
			alert('検索ワードを入力してください');
			return false;
		}
		if ((condition === 'orderNo' || condition === 'orderDate') && isNaN(detail)){
			alert('分かち書きなしで数字だけで入力してください');
			return false;			
		}

		// 폼 데이터를 가져와 URL에 추가
		var url = "orderSearch.do?condition=" + condition + "&detail="
				+ encodeURIComponent(detail);
		var title = '発注要請検索';

		// 새 창 열기
		window.open(url, title, 'width=1400, height=700');
	}
</script>
</html>

