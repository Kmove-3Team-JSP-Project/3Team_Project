<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<form action="orderRegister.do" method="post">
	<input type="hidden" name="progress" value="進行中">
		<div id="my-div">登録窓</div>
		<table>
			<tr>
				<td style="text-align: center;">要請No(自動発行)</td>
				<td><input type="text" value="${orderNo}" readonly size="13"></td>
			</tr>

			<tr>
				<td style="text-align: center;">担当者</td>
				<td><input type="text" value="${authUser.name}" readonly
					size="13"></td>
			</tr>
			<tr>
				<td style="text-align: center;">品目名</td>
				<td><select name="itemName">
						<c:forEach var="itemName" items="${itemNames}">
							<option value="${itemName}">${itemName}</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td style="text-align: center;">単価</td>
				<td><input type="text" value="${param.unitPrice}"
					id="unitPriceInput" size="13" readonly></td>

			</tr>
			<tr>
				<td style="text-align: center;">数量</td>
				<td><input type="text" value="${param.amount}" name=amount
					size="13"></td>

			</tr>
			<tr>
				<td style="text-align: center;">金額</td>
				<td><input type="text" value="${param.price}" maxlength="8"
					size="13" readonly></td>
			</tr>
			<tr>
				<td style="text-align: center;">取引先名</td>
				<td><select name="companyName">
						<c:forEach var="companyName" items="${companyList}">
							<option value="${companyList}">${companyList}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: center;">倉庫名</td>
				<td><select name="storageName">
						<c:forEach var="storageName" items="${storageList}">
							<option value="${storageList}">${storageList}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: center;">納期日</td>
				<td><input type="date" id="Date" name="Date"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="button" value="登録"
					style="font-size: 20px; width: 70px; height: 40px; margin-top: 10px;"
					onclick="Check(this.form);" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		var itemList = $
		{
			itemListJson
		}
		; // itemListJson을 JavaScript 객체로 파싱

		var itemNameInput = document.getElementById("itemNameInput");
		var unitPriceInput = document.getElementById("unitPriceInput");

		itemNameInput.addEventListener("change", function() {
			var selectedValue = itemNameInput.value;

			for (var i = 0; i < itemList.length; i++) {
				if (itemList[i].itemName === selectedValue) {
					unitPriceInput.value = itemList[i].unitPrice;
					break;
				}
			}
		});

		var currentDate = new Date().toISOString().split("T")[0];
		document.getElementById("Date").value = currentDate;

		var now_utc = Date.now();
		var timeOff = new Date().getTimezoneOffset() * 60000;
		var today = new Date(now_utc - timeOff).toISOString().split("T")[0];
		document.getElementById("Date").setAttribute("min", today);
	});

	function Check(form) {
		var itemName = form.itemName.value;
		var amount = form.amount.value;
		var companyName = form.companyName.value;
		var storageName = form.storageName.value;
		var date = form.Date.value;

		if (itemName === '' || amount === '' || companyName === ''
				|| storageName === '' || date === '') {
			alert('모든 필드를 채워주세요.');
			return false;
		}

		form.submit();
	}
</script>