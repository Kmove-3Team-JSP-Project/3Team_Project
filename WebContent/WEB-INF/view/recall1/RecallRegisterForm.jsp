<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="recall.service.RecallRegisterService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%
RecallRegisterService registerService = new RecallRegisterService();
%>
<%
List<String> stock_Name = registerService.getStockName();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<form action="recallRegister.do" method="post">
		<input type="hidden" name="unit_Price" value="${param.unit_price}" value="">
		<input type="hidden" name="progress" value="進行中">
		<div id="my-div">登録窓</div>
		<table>
			<tr>
				<td style="text-align: center;">処理No(自動発行)</td>
				<td><input type="text" name="recallNo" value="${recall_No}"
					readonly size="13"></td>
			</tr>
			<tr>
				<td style="text-align: center;">担当者</td>
				<td><input type="text" name="member_Name"
					value="${authUser.name}" readonly size="13"></td>
			</tr>
			<tr>
				<td style="text-align: center;">在庫名</td>
				<td><select name="stock_Name" id="stock_NameInput">
						<c:forEach var="stock_Name" items="${stock_Name}">
							<option value="${stock_Name}">${stock_Name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: center;">数量</td>
				<td><input type="text" name="amount" id="amountInput" size="13"><input type="text" name="amount" id="amountInput" size="13"></td>
			</tr>
			<tr>
				<td style="text-align: center;">倉庫名</td>
				<td><input type="text" name="storage_Name" id="storageInput"
					value="" size="13" readonly></td>
				
			</tr>
			<tr>
				<td style="text-align: center;">処理日</td>
				<td><input type="date" id="Date" name="process_Date"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="登録"
					style="font-size: 20px; width: 70px; height: 40px; margin-top: 10px;"
					onclick="Check(this.form);" /></td>
			</tr>
		</table>
	</form>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function () {
    	var storageInput = document.getElementById("storageInput");
      	var amountInput = document.getElementsByName("amount")[0];
      	var unit_PriceInput = document.getElementsByName("unit_PriceInput");
      	
      	amountInput.addEventListener("input", updateAmount);
      	
        function updateAmount() {
            var amount = parseInt(amountInput.value);
            var originalAmount = parseInt(amountInput.value, 10);

            if (!isNaN(originalAmount)) {
                if (amount < originalAmount) {
                    updateProcess('処理');
                } else if (amount === originalAmount) {
                    updateProcess('除去');
                }
            }
        }

        function updateProcess(processValue) {
            var recall_No = document.getElementsByName("recall_No")[0].value;

            if (recall_No !== '') {
                var updateProcessUrl = 'updateProcessEndpoint?recall_No=' + recall_No + '&process=' + processValue;

                fetch(updateProcessUrl, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                    })
                    .catch((error) => {
                        console.error('error:', error);
                    });
            }
        }
    });
    
    
    var itemList = JSON.parse('${stockDetails1Json}'); // itemListJson을 JavaScript 객체로 파싱 StockNamesWithStockAmount
    var itemList2 = JSON.parse('${stockDetails2Json}'); // StockNamesWithStorageNames
    var itemList3 = JSON.parse('${itemDetailsJson}'); // unitPrice
    
    var unitPriceInput = document.getElementById("unitPriceInput");
	var stock_NameInput = document.getElementById("stock_NameInput");
	var storageInput = document.getElementById("storageInput");
	
	//StockNamesWithStockAmount // StockNamesWithStorageNames // unitPrice
	stock_NameInput.addEventListener("change", function() {
		var selectedValue = stock_NameInput.value;
		var keys1 = Object.keys(itemList);
		var keys2 = Object.keys(itemList2);
		var keys3 = Object.keys(itemList3);
		
		for (var i = 0; i < keys1.length; i++) {
			var key = keys[i];
			if (key === selectedValue) {
				unitPriceInput.value = itemList[key];
				break;
			}
			
		}
	});

function Check(form) { var stock_Name = form.stock_Name.value; var
amount = form.amount.value; var storageName = form.storageName.value;
var date = form.Date.value; if (stock_Name === '' || amount === '' ||
storageName === '' || date === '') {
	alert('すべてのフィールドを埋めてください');
	return false; } form.submit(); }

</script>
</html>