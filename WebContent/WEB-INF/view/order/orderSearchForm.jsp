<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索窓</title>
<style type="text/css">
#my-div {
	margin-left:10px;
	margin-top: 5px;
	height: 30px;
	line-height: 50px;
	font-weight: bold;
	font-size: 20px;
}

#search_T {
	margin-top: 10px;
	margin-left: auto;
	margin-right: auto;
	width: 900px;
	border: solid #444444;
	font-size: 12px;
}

#result_T {
	margin-top: 10px;
	margin-left: auto;
	margin-right: auto;
	width: 900px;
	heigth: 500px;
	border: 1px solid #444444;
}

#text_td {
	padding: 1px 5px 1px 5px;
	style: text-align: center;
}

td {
	white-space: nowrap;
	border: 1px solid #444444;
}
</style>
</head>
<body>
	<form method="post" action="orderSearch.do">
		<div id="my-div">条件入力</div>
		<input type="button" value="検索" onclick="#" style="margin-botton: 20px; margin-left: 800px;" /> 
			<input type="button" value="初期化" onclick="#" style="margin-botton: 20px;" />
		<table id="search_T">
			<tr>
				<td id="text_td">要請No</td>
				<td><input type="text" name="orderNo" size="8"></td>

				<td id="text_td">担当者</td>
				<td><input type="text" name="memberName" size="8"></td>

				<td id="text_td">品目名</td>
				<td><input type="text" name="itemName" size="8"></td>


				<td id="text_td">取引先名</td>
				<td><input type="text" name="companyName" size="8"></td>

				<td id="text_td">倉庫名</td>
				<td><input type="text" name="storageName" size="8"></td>

				<td id="text_td">納期日</td>
				<td><input type="date" name="orderDate" size="8"></td>
			</tr>
		</table>
		<div id="my-div">検索結果</div>
		<%@ include file="orderSearchResult.jsp" %>
	</form>
</body>
</html>