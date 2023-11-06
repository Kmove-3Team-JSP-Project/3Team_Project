<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録</title>

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
		var inputData1 = document.getElementById("name").value;
		var inputData3 = document.getElementById("password").value;
		var inputData4 = document.getElementById("confirmPassword").value;
		var inputData5 = document.getElementById("mail").value;
		var inputData6 = document.getElementById("position").value;

		if (inputData1 === "" || inputData3 === "" || inputData4 === ""
				|| inputData5 === "" || inputData6 === "") {
			alert("데이터가 입력되지 않았습니다.");
		}
	}
</script>

</head>
<body>

	<div class="login-form">
		<h1>会員登録</h1>
		<form action="join.do" method="post">
			<div class="input-group">
				<label for="name">氏名</label> <input type="text" id="name"
					name="name">
			</div>

			<div class="input-group">
				<label for="memberId">社員番号(ID)</label> <input type="text"
					id="memberId" name="memberId" value="${newMemberCount}" readonly>
			</div>

			<div class="input-group">
				<label for="password">パスワード</label> <input type="password"
					id="password" name="password">
			</div>

			<div class="input-group">
				<label for="confirmPassword">パスワード確認</label> <input type="password"
					id="confirmPassword" name="confirmPassword">
					<c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
			</div>

			<div class="input-group">
				<label for="mail">メール</label> <input type="text" id="mail"
					name="mail">
			</div>

			<div class="input-group">
				<label for="position">役職</label> <input type="text" id="position"
					name="position">
			</div>

			<div class="button-group">
				<button type="submit" onclick="checkData()">会員登録</button>
			</div>
		</form>
	</div>
</body>
</html>