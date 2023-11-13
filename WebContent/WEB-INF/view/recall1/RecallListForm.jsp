<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>不良処理リスト</title>
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
		<form action="recallList.do" method="post">

			<div id="my-div">[不良処理リスト]</div>
			<table>
				<tr>
					<td>不良処理番号</td>
					<td>担当者</td>
					<td>品目名</td>
					<td>単価</td>
					<td>数量</td>
					<td>倉庫名</td>
					<td>処理日</td>
					<td>処理過程</td>
				</tr>

				<c:if test="${recallPage.hasNoRecall()}">
					<tr>
						<td colspan="8">登録された要請はありません。</td>
					</tr>
				</c:if>
				<c:forEach var="recall" items="${recallPage.content}">
					<tr>
						<td>${recall.recall_No}</td>
						<td>${recall.member_Name}</td>
						<td>${recall.stock_Name}</td>
						<td>${recall.unit_Price}</td>
						<td>${recall.amount}</td>
						<td>${recall.storageName}</td>
						<td><fmt:formatDate value="${recall.process_Date}"
								pattern="yyyy-MM-dd" /></td>
						<td><select id="processSelect_${recall.recall_No}"
							onchange="updateProcess(${recall.recall_No})">
								<option value="InProgress">進行中</option>
								<option value="Completed">完了</option>
								<option value="Cancelled">キャンセル</option>
						</select></td>

					</tr>
				</c:forEach>

				<c:if test="${recallPage.hasRecall()}">
					<tr>
						<td colspan="8"><c:if test="${recallPage.startPage > 5}">
								<a href="recallList.do?pageNo=${recallPage.startPage - 5}">[前のページ]</a>
							</c:if> <c:forEach var="pNo" begin="${recallPage.startPage}"
								end="${recallPage.endPage}">
								<a href="recallList.do?pageNo=${pNo}">[${pNO}]</a>
							</c:forEach> <c:if test="${recallPage.endPage < recallPage.totalPages}">
								<a href="recallList.do?pageNo=${recallPage.startPage + 5}">[次のページ]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>

			<input type="button" value="要請登録"
				style="font-size: 20px; width: 70px; height: 40px; margin-top: 30px;"
				onclick="window.open('recallRegister.do', '不良処理要請登録', 'width=700, height=700')" />
		</form>
	</div>

</body>
<script type="text/javascript">
function updateProcess(recallNo) {
    var selectElement = document.getElementById(`processSelect_${recallNo}`);
    var selectedValue = selectElement.value;
    
    if (selectElement.disabled) {
    	return;
    }

    selectElement.disabled = true; 
    
    document.getElementById("recallNoInput").value = recallNo;
    document.getElementById("processInput").value = selectedValue;
    
    document.form.submit();
}
</script>
</html>