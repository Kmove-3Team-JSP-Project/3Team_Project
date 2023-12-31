<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>倉庫リスト</title>
<style>
.centered-cell {
	text-align: center;
}

.righted-cell {
	text-align: right;
}
</style>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<script>
		function redirectToNewPage() {
			// 다른 페이지의 URL을 여기에 지정
			var newPageURL = "storageRegister.do";
			window.location.href = newPageURL;
		}
	</script>
	<script>
		function openNewWindow() {
			// 새로운 윈도우를 열고 해당 윈도우에 새로운 페이지를 로드합니다.
			var newPageURL = "storageSearch.do";
			window.open(newPageURL, "_blank", "width=600,height=400");
		}
	</script>
	<div align="center">
		<form action="storageSearch.do" method="get" target="_blank">
			<h1>倉庫リスト</h1>
			<table border="1" width="60%">
				<tr>
					<th class="centered-cell">倉庫コード</th>
					<th class="centered-cell">倉庫名</th>
					<th class="centered-cell">倉庫住所</th>
					<th class="centered-cell">使用有無</th>
				</tr>
				<c:if test="${storagePage.hasNoStorage() }">
					<tr>
						<td colspan="4">창고가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="storage" items="${storagePage.content}">
					<tr>
						<td class="centered-cell">${storage.storageCode}</td>
						<td class="centered-cell">${storage.storageName}</td>
						<td class="centered-cell">${storage.storageAddress}</td>
						<td class="centered-cell">${storage.storageUse}</td>
					</tr>
				</c:forEach>
				<c:if test="${storagePage.hasStorage()}">
					<tr>
						<td colspan="4"><c:if test="${storagePage.startPage > 5}">
								<a href="storageList.do?pageNo=${storagePage.startPage-5}">[이전]</a>
							</c:if> <c:forEach var="pNo" begin="${storagePage.startPage}"
								end="${storagePage.endPage}">
								<a href="storageList.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach> <c:if test="${storagePage.endPage < storagePage.totalPages}">
								<a href="storageList.do?pageNo=${storagePage.startPage + 5 }">[다음]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>



			<table border="1" width="60%">
				<tr>
					<td class="righted-cell"><select name="searchCriteria">
							<option value="STORAGE_ID">倉庫コード</option>
							<option value="STORAGE_NAME">倉庫名</option>
							<option value="STORAGE_ADDRESS">倉庫住所</option>
							<option value="USE">使用有無</option>
					</select> <input type="search" name="searchValue"> <input
						type="submit" value="検索"> <input
						type="button" value="登録" onclick="redirectToNewPage()"></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>