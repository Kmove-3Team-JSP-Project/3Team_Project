<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>倉庫移動登録</title>
<style>

.centered-cell {
    text-align: center;
}
.righted-cell{
	text-align: right;
}


    </style>
</head>
<body>
	<script>
		function showSuccessAlert() {
            alert("倉庫リストの登録が完了しました。");
            window.location.href = "idou.jsp";
        }
	</script>
	<div align="center">
		<h1>倉庫移動登録</h1>
		<!--   倉庫コード: <input type="text"><br/>
倉庫名: <input type="text"><br/>
倉庫住所: <input type="text"><br/>
使用有無: <input type="text"><br/> -->
		<table border="1" width="60%">
			<tr>
				<th class="centered-cell">送る倉庫</th>
				<th class="centered-cell"><input type="text"></th>
			</tr>
			<tr>
				<th class="centered-cell">受け取る倉庫</th>
				<th class="centered-cell"><input type="text"></th>
			</tr>
			<tr>
				<th class="centered-cell">品目名</th>
				<th class="centered-cell"><input type="text"></th>
			</tr>
			<tr>
				<th class="centered-cell">数量</th>
				<th class="centered-cell"><input type="text"></th>
			</tr>
			<tr>
				<th class="centered-cell">担当者</th>
				<th class="centered-cell"><input type="text"></th>
			</tr>
		</table>
		<table border="1" width="60%">
			<tr>
				<th class="righted-cell"><input type="button" value="登録"
					onclick="showSuccessAlert()"></th>

			</tr>
		</table>

	</div>
</body>
</html>