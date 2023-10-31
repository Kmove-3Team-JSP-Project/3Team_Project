<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録窓</title>
<style type="text/css">
#my-div {
	height: 50px;
	text-align: center;
	line-height: 50px;
}

table {
	table-layout: fixed;
	margin-left: auto;
	margin-right: auto;
	width: 400px;
	heigth: 250px;
	border: solid #444444;
}

td {
	white-space: nowrap;
	border: 1px solid #444444;
}
</style>

</head>
<body>
	<form method="post">
		<div id="my-div">登録窓</div>
		<table>
			<tr>
				<td style="text-align: center;">要請No(自動発行)</td>
				<td><input type="text" value="${30001}"
					readonly size="13"></td>
			</tr>

			<tr>
				<td style="text-align: center;">担当者</td>
				<td><input type="text" value="" size="13"><input type="button" value="検索"></td>
			</tr>
			<tr>
				<td style="text-align: center;">品目名</td>
				<td><input type="text" value="" size="13"><input type="button" value="検索"></td>

			</tr>
			<tr>
				<td style="text-align: center;">単価</td>
				<td><input type="text" value="" size="13" readonly>
				</td>

			</tr>
			<tr>
				<td style="text-align: center;">数量</td>
				<td><input type="text" value="" size="13">
				</td>

			</tr>
			<tr>
				<td style="text-align: center;">金額</td>
				<td><input type="text" value="" maxlength="8" size="13" readonly></td>
			</tr>
			<tr>
				<td style="text-align: center;">取引先名</td>
				<td><input type="text" value="" size="13"><input type="button" value="検索"></td>
			</tr>
			<tr>
				<td style="text-align: center;">倉庫名</td>
				<td><input type="text" value="" size="13"><input type="button" value="検索"></td>
			</tr>
			<tr>
				<td style="text-align: center;">納期日</td>
				<td><input type="date" value="" size="13"></td>
			</tr>
		
			<tr>
				<td colspan="2" align="center"><input type="button" value="登録"
					style="font-size: 20px; width: 70px; height: 40px; margin-top: 10px;"
					onclick="window.close();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>