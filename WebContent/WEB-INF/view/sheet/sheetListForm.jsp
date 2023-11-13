<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注書</title>
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
		<form action="sheetList.do" method="post">

			<h1 style="text-align: center;">[発注書]</h1>
			<table>
				<tr>
					<td>発注書No</td>
					<td>担当者</td>
					<td>物品名</td>
					<td>単価</td>
					<td>数量</td>
					<td>金額</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>日付</td>
					<td>状態</td>
				</tr>

				<c:if test="${sheetPage.hasNoSheets()}">
					<tr>
						<td colspan="10">登録された発注書が存在しません。</td>
					</tr>
				</c:if>
				<c:forEach var="sheet" items="${sheetPage.content}">
					<tr>
						<td>${sheet.listNo}</td>
						<td>${sheet.memberName}</td>
						<td>${sheet.productName}</td>
						<td>${sheet.unitPrice}</td>
						<td>${sheet.amount}</td>
						<td>${sheet.price}</td>
						<td>${sheet.companyName}</td>
						<td>${sheet.storageName}</td>
						<td><fmt:formatDate value="${sheet.listDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${sheet.process}</td>
					</tr>
				</c:forEach>
				<c:if test="${sheetPage.hasSheets()}">
					<tr>
						<td colspan="10"><c:if test="${sheetPage.startPage > 5}">
								<a href="sheetList.do?pageNo=${sheetPage.startPage - 5}">[前のページ]</a>
							</c:if> <c:forEach var="pNo" begin="${sheetPage.startPage}"
								end="${sheetPage.endPage}">
								<a href="sheetList.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach> <c:if test="${sheetPage.endPage < sheetPage.totalPages}">
								<a href="sheetList.do?pageNo=${sheetPage.startPage + 5}">[次のページ]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>
		</form>
		<form name="Data">
			<select name="condition"
				style="margin-top: 20px; margin-left: 770px; font-size: 15px;">
				<option value="listNo">発注書No</option>
				<option value="memberName">担当者</option>
				<option value="productName">物品名</option>
				<option value="companyName">取引先名</option>
				<option value="storageName">倉庫名</option>
				<option value="listDate">日付</option>
				<option value="process">状態</option>
			</select> <input type="text" placeholder="検索ワード入力" name="detail"
				maxlength="50" style="margin-top: 20px; font-size: 15px;"> <input
				type="button" value="検索"
				style="font-size: 15px; width: 50px; height: 30px; margin-top: 20px;"
				onclick="Check(this.form);" />
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
		if ((condition === 'listNo' || condition === 'listDate')
				&& isNaN(detail)) {
			alert('分かち書きなしで数字だけで入力してください');
			return false;
		}

		// 폼 데이터를 가져와 URL에 추가
		var url = "sheetSearch.do?condition=" + condition + "&detail="
				+ encodeURIComponent(detail);
		var title = '発注書検索';

		// 새 창 열기
		window.open(url, title, 'width=1400, height=700');
	}
</script>
</html>