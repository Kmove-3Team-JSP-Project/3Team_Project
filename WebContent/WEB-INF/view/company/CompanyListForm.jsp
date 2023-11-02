<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先リスト</title>
</head>
<body>
	<h1>
		<div align="center">[取引先リスト]</div>
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
			<c:if test="${companyPage.hasNoCompany()}">
				<tr>
					<td colspan="4">거래처 코드가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="company" items="${companyPage.content}">
				<tr>
					<td>${company.name}</td>
					<td><a
						href="Listdo?no=${company.name }&pageNo=${companyPage.currentPage}">
					</a></td>
					<td>${company.name}</td>
					<td>${company.readCount }</td>
				</tr>
			</c:forEach>

		</table>



		<div align="center">
			<form action="CompanySearchForm.jsp" method="post">
				<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
					<option value="取引先コード">取引先コード</option>
					<option value="取引先名">取引先名</option>
					<option value="担当者">担当者</option>
					<option value="電話番号">電話番号</option>
					<option value="所有倉庫">所有倉庫</option>
				</select> <input type="text" name="keyword" placeholder="入力"> <input
					type="submit" value="検索"> <input type="button" value="登録"
					onclick="window.location.href='CompanyRegisterForm.jsp'" />





			</form>

		</div>


	</div>

</body>
</html>
