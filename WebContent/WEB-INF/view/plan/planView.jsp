<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注計画</title>
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

				<tr>
					<td>40001</td>
					<td>担当者</td>
					<td>在庫名</td>
					<td>3000</td>
					<td>10</td>
					<td>30000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>発注出庫日付</td>
					<td>終結可否</td>
				</tr>

				<tr>
					<td>40003</td>
					<td>担当者</td>
					<td>在庫名</td>
					<td>30</td>
					<td>10</td>
					<td>300</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>発注出庫日付</td>
					<td>終結可否</td>
				</tr>
				<tr>
					<td>40004</td>
					<td>担当者</td>
					<td>在庫名</td>
					<td>2000</td>
					<td>10</td>
					<td>20000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>発注出庫日付</td>
					<td>終結可否</td>
				</tr>
				<tr>
					<td>40005</td>
					<td>担当者</td>
					<td>在庫名</td>
					<td>3000</td>
					<td>10</td>
					<td>30000</td>
					<td>取引先名</td>
					<td>倉庫名</td>
					<td>発注出庫日付</td>
					<td>終結可否</td>
				</tr>


			</table>
			<input type="button" value="検索"
				style="font-size: 20px; width: 70px; height: 40px; margin-top: 30px; margin-left: 850px;"
				onclick="window.open('<%=request.getContextPath()%>/view/plan/planSearch.jsp', '発注計画検索', 'width=1200, height=700')" />
			<input type="button" value="登録"
				style="font-size: 20px; width: 70px; height: 40px; margin-top: 30px;"
				onclick="window.open('<%=request.getContextPath()%>/view/plan/planForm.jsp', '発注計画登録', 'width=1000, height=700')" />
		</form>
	</div>

</body>
</html>