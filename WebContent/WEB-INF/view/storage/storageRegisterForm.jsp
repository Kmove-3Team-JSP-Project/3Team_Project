<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>倉庫リスト登録</title>
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
	<script>
		function showSuccessAlert() {
			alert("倉庫リストの登録が完了しました。");
			window.location.href = "storageList.do";
		}
	</script>
	<div align="center">
		<form action="storageRegister.do" method="post">
			<h1>倉庫リスト登録</h1>
			<!--   倉庫コード: <input type="text"><br/>
倉庫名: <input type="text"><br/>
倉庫住所: <input type="text"><br/>
使用有無: <input type="text"><br/> -->
			<table border="1" width="60%">
				<tr>
					<th class="centered-cell">倉庫コード</th>
					<th class="centered-cell"><input type="text" name="storage_id"
						value="${newStorageCode}"></th>
				</tr>
				<tr>
					<th class="centered-cell">倉庫名</th>
					<th class="centered-cell"><input type="text"
						name="storage_name"></th>
				</tr>
				<tr>
					<th class="centered-cell">倉庫住所</th>
					<th class="centered-cell"><input type="text"
						name="storage_Address"></th>
				</tr>
				<tr>
					<th class="centered-cell">使用有無</th>
					<th class="centered-cell"><input type="text" name="Use"></th>
				</tr>
			</table>
			<table border="1" width="60%">
				<tr>
					<th class="righted-cell"><input type="submit" value="登録">
						<!-- onclick="showSuccessAlert();" -->
						
				</tr>
			</table>

		</form>
	</div>
</body>
</html>