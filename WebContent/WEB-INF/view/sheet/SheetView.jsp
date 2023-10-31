<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注書</title>
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
<%-- <jsp:include page="#" flush="false" /> --%>
<body>
	<h1>Header</h1>
	<div id="wrap">
		<form action="#" method="post">
			<div id="my-div">[発注書]</div>
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

				<tr>
					<td>30001</td>
					<td>担当者</td>
					<td>物品名</td>
					<td>3000</td>
					<td>10</td>
					<td>30000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>日付</td>
					<td>状態</td>
				</tr>

				<tr>
					<td>40003</td>
					<td>担当者</td>
					<td>物品名</td>
					<td>30</td>
					<td>10</td>
					<td>300</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>日付</td>
					<td>状態</td>
				</tr>
				<tr>
					<td>30004</td>
					<td>担当者</td>
					<td>物品名</td>
					<td>2000</td>
					<td>10</td>
					<td>20000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>日付</td>
					<td>状態</td>
				</tr>
				<tr>
					<td>40005</td>
					<td>担当者</td>
					<td>物品名</td>
					<td>3000</td>
					<td>10</td>
					<td>30000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>日付</td>
					<td>状態</td>
				</tr>


			</table>
			<input type="button" value="検索" style="font-size: 20px; width: 70px; height: 40px;
			margin-top:30px; margin-left:925px;"
			onclick="window.open('<%=request.getContextPath()%>/SheetSearch.jsp', '発注書検索', 'width=1200, height=700')" />
			</form>
	</div>

</body>
</html>