<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購買要請</title>
</head>
<body>
	<h1>
		<%@ include file="/header.jsp"%>
		<div align="center">[購買要請]</div>
	</h1>
	<div align="center">
		<table border='1' width='50%'>
			<tr>
				<th>要請No</th>
				<th>倉庫コード</th>
				<th>品目名</th>
				<th>単価</th>
				<th>数量</th>
				<th>総額</th>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
			<tr>
				<td>AUTO.NUM</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
				<td>NULL</td>
			</tr>
		</table>
		<br>
		<button id="requestButton">要請</button>
		<button id="cancelButton">キャンセル</button>
	</div>

	<script>
		document.getElementById("requestButton").addEventListener("click",
				function() {
					var confirmResult = confirm("要請しますか？");
					if (confirmResult) {
						alert("要請が完了しました.");
					} else {
						alert("要請がキャンセルされました.");
					}
				});

		document.getElementById("cancelButton").addEventListener("click",
				function() {
					// 여기에 다음 폼으로 이동하는 코드를 추가하세요.
					window.location.href = "BuyListForm.jsp";
				});
	</script>
	</div>
</body>
</html>