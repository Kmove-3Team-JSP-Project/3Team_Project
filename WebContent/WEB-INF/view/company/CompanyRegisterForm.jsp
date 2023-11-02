<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先登録</title>
</head>
<body>
	<h1>
		<div align="center">[取引先登録]</div>
	</h1>
	<
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
				<td>APPLE</td>
				<td>KIM</td>
				<td>01098765432</td>
				<td>SUWON</td>
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
				<td></td>
				<td>GOOGLE</td>
				<td>LEE</td>
				<td>01044444444</td>
				<td>TOKYO</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>NAVER</td>
				<td>SOO</td>
				<td>01082826464</td>
				<td>BUSAN</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>LINE</td>
				<td>JOON</td>
				<td>01012345678</td>
				<td>JEJU</td>
				<td></td>
			</tr>
		</table>

		<br>
		<button id="requestButton">登録</button>
		<button id="cancelButton">キャンセル</button>
	</div>

	<script>
		document.getElementById("requestButton").addEventListener("click",
				function() {
					var confirmResult = confirm("登録しますか？");
					if (confirmResult) {
						alert("登録が完了しました.");
					} else {
						alert("登録がキャンセルされました.");
					}
				});
		document.getElementById("cancelButton").addEventListener("click",
				function() {
					// 여기에 다음 폼으로 이동하는 코드를 추가하세요.
					window.location.href = "CompanyListForm.jsp";
				});
	</script>
	</div>
</body>
</html>