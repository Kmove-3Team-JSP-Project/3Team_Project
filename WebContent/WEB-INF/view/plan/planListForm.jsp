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
h1 {
	text-align: center;
}

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
	width: 1000px;
	heigth: 500px;
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
		<form action="planList.do" method="post">
			
			<div id="my-div">[発注計画]</div>
			<table>
				<tr>
					<td>計画No</td>
					<td>担当者</td>
					<td>在庫名</td>
					<td>単価</td>
					<td>数量</td>
					<td>金額</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>発注出庫日付</td>
					<td>終結可否</td>
				</tr>

				<c:if test="${planPage.hasNoPlans()}">
					<tr>
						<td colspan="10">登録された発注要請が存在しません。</td>
					</tr>
				</c:if>
				<c:forEach var="plan" items="${planPage.content}">
					<tr>
						<td>${plan.planNo}</td>
						<td>${plan.memberName}</td>
						<td>${plan.stockName}</td>
						<td>${plan.unitPrice}</td>
						<td>${plan.amount}</td>
						<td>${plan.price}</td>
						<td>${plan.companyName}</td>
						<td>${plan.storageName}</td>
						<td><fmt:formatDate value="${plan.planDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><select id="progressSelect_${plan.planNo}"
							onchange="updateProgress(${plan.planNo})">
								<option value="InProgress">進行中</option>
								<option value="Completed">完了</option>
								<option value="Cancelled">キャンセル</option>
						</select></td>



					</tr>
				</c:forEach>
				<c:if test="${planPage.hasPlans()}">
					<tr>
						<td colspan="10"><c:if test="${planPage.startPage > 5}">
								<a href="planList.do?pageNo=${planPage.startPage - 5}">[前のページ]</a>
							</c:if> <c:forEach var="pNo" begin="${planPage.startPage}"
								end="${planPage.endPage}">
								<a href="planList.do?pageNo=${pNo}">[${pNO}]</a>
							</c:forEach> <c:if test="${planPage.endPage < planPage.totalPages}">
								<a href="planList.do?pageNo=${planPage.startPage + 5}">[次のページ]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>
			
			<input type="hidden" id="orderNoInput" name="orderNo" value="">
			<input type="hidden" id="progressInput" name="progress" value="">
			<input type="button" value="検索"
				style="font-size: 20px; width: 70px; height: 40px; margin-top: 30px; margin-left: 850px;"
				onclick="window.open('orderSearch.do', '発注計画検索', 'width=1200, height=700')" />
			<input type="button" value="登録"
				style="font-size: 20px; width: 70px; height: 40px; margin-top: 30px;"
				onclick="window.open('orderRegister.do', '発注計画登録', 'width=700, height=700')" />
		</form>
	</div>

</body>

</html>
<!-- <script type="text/javascript">
function updateProgress(orderNo) {
    var selectElement = document.getElementById(`progressSelect_${orderNo}`);
    var selectedValue = selectElement.value;
    
    if (selectElement.disabled) {
        // 이미 선택되었으므로 아무것도 하지 않음
        return;
    }

    selectElement.disabled = true; // 선택 후 비활성화


</script> -->
<script type="text/javascript">
function updateProgress(orderNo) {
    var selectElement = document.getElementById(`progressSelect_${orderNo}`);
    var selectedValue = selectElement.value;
    
    if (selectElement.disabled) {
        // 이미 선택되었으므로 아무것도 하지 않음
        return;
    }

    selectElement.disabled = true; // 선택 후 비활성화
    
    // Hidden input에 데이터 설정
    document.getElementById("orderNoInput").value = orderNo;
    document.getElementById("progressInput").value = selectedValue;
}

function submitForm() {
    document.forms[0].submit();
}

</script>