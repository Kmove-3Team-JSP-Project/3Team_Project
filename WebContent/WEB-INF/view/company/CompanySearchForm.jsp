<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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

	<h2>
		<div align="center">[検索語:担当者]</div>
	</h2>

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
			<tr>
				<td></td>
				<td>DAUM</td>
				<td>JOON</td>
				<td>01012345678</td>
				<td>SEOUL</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>YAHOO</td>
				<td>JOON</td>
				<td>01012345678</td>
				<td>OSAKA</td>
				<td></td>
			</tr>
			<tr>
			<tr>
				<td></td>
				<td>LINE</td>
				<td>JOON</td>
				<td>01012345678</td>
				<td>JEJU</td>
				<td></td>
			</tr>
		</table>
	</div>
	<h2>
		<div align="center">
			<a href="CompanyListForm.jsp">[前ページに戻る。]</a>
		</div>
	</h2>
</body>
</html>