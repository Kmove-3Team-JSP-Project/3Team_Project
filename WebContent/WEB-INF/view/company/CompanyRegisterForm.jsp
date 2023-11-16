<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先登録</title>
<style>
body {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
}

.logo {
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
}

.login-form {
	text-align: center;
	background-color: #f4f4f4;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.input-group {
	margin: 10px 0;
}

.input-group label {
	display: block;
}

.input-group input {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.button-group {
	margin-top: 20px;
}

.button-group button {
	padding: 10px 20px;
	border: none;
	background-color: #0073e6;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.button-group a {
	text-decoration: none;
	margin-left: 10px;
}
</style>
<script type="text/javascript">
	function checkData() {
		var inputData1 = document.getElementById("name").value; // 수정: "name"으로 변경
		var inputData2 = document.getElementById("master").value;
		var inputData3 = document.getElementById("address").value;
		var inputData4 = document.getElementById("phone").value;
		var inputData5 = document.getElementById("myStroage").value; // 수정: "myStroage"로 변경

		if (inputData1 === "" || inputData2 === "" || inputData3 === ""
				|| inputData4 === "" || inputData5 === "") {
			alert("데이터가 입력되지 않았습니다.");
		}
	}
</script>

</head>
<body>

	<div class="login-form">
		<h1>取引先登録</h1>
		<form action="companyRegister.do" method="post">
			<div class="input-group">
				<label for="company_name">取引先名</label> <input type="text" id="name"
					name="company_name">
			</div>

			<div class="input-group">
				<label for="master">担当者</label> <input type="text" id="master"
					name="master">
			</div>

			<div class="input-group">
				<label for="phone">電話番号</label> <input type="text" id="phone"
					name="phone">
			</div>

			<div class="input-group">
				<label for="address">住所</label> <input type="text" id="address"
					name="address">
			</div>

			<div class="input-group">
				<label for="myStorage">所有倉庫</label> <input type="text"
					id="myStroage" name="myStorage">
			</div>

			<br>
			<button type="submit" id="requestButton" onclick="checkData()">登録</button>
			<button type="button" id="cancelButton">キャンセル</button>
		</form>

			<script>
				document.getElementById("requestButton").addEventListener(
						"click", function() {
							var confirmResult = confirm("登録しますか？");
							if (confirmResult) {
								alert("登録が完了しました.");

								// 추가: 등록이 성공했을 때, 전 페이지로 이동하는 코드
								window.location.href = document.referrer; // 이전 페이지로 이동
							} else {
								alert("登録がキャンセルされました.");
							}
						});

				document.getElementById("cancelButton").addEventListener(
						"click", function() {
							// 여기에 다음 폼으로 이동하는 코드를 추가하세요.
							window.location.href = "companyList.do";
						});
			</script>



	</div>
</body>
</html>
