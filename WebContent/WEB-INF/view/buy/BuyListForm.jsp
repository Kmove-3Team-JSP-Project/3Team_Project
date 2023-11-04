<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購買リスト</title>
</head>
<body>
	<h1>
		<%@ include file="/header.jsp"%>
		<div align="center">[購買リスト]</div>
	</h1>


	<div align="center">
		<table border='1' width='50%'>
			<tr>
				<th>日付No</th>
				<th>取引先名</th>
				<th>数量</th>
				<th>品目名</th>
				<th>単価</th>
				<th>購買日付</th>
				<th>担当者</th>
			</tr>
			<tr>
				<td></td>
				<td>DAUM</td>
				<td>10</td>
				<td></td>
				<td>33000</td>
				<td>2023-10-27</td>
				<td>JOON</td>
			</tr>
			<tr>
				<td></td>
				<td>APPLE</td>
				<td>3</td>
				<td></td>
				<td>59000</td>
				<td>2023-10-16</td>
				<td>SON</td>
			</tr>
			<tr>
				<td></td>
				<td>YAHOO</td>
				<td>5</td>
				<td></td>
				<td>10000</td>
				<td>2023-09-15</td>
				<td>JOON</td>
			</tr>
			<tr>
				<td></td>
				<td>GOOGLE</td>
				<td>7</td>
				<td></td>
				<td>5800</td>
				<td>2023-10-05</td>
				<td>JAY</td>
			</tr>
			<tr>
				<td></td>
				<td>NAVER</td>
				<td>100</td>
				<td></td>
				<td>3600</td>
				<td>2022-11-05</td>
				<td>HEO</td>
			</tr>
			<tr>
				<td></td>
				<td>SAMSUNG</td>
				<td>6</td>
				<td></td>
				<td>46000</td>
				<td>2023-01-16</td>
				<td>JOON</td>
			</tr>
			<tr>
				<td></td>
				<td>KAKAO</td>
				<td>4</td>
				<td></td>
				<td>3000</td>
				<td>2023-10-24</td>
				<td>SKY</td>
			</tr>
		</table>



		<div align="center">
			<form action="BuySearchForm.jsp" method="post">
				<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
					<option value="日付No">取引先コード</option>
					<option value="取引先名">取引先名</option>
					<option value="品目名">担当者</option>
					<option value="購買日付">電話番号</option>
					<option value="担当者">所有倉庫</option>
				</select> <input type="text" name="keyword" placeholder="入力"> <input
					type="submit" value="検索"> <input type="button" value="購買要請"
					onclick='BuyRequest()' />

				<script>
					function BuyRequest() {
						window.location.href = "BuyRequestPage.jsp";
					}
				</script>
			</form>

		</div>


	</div>

</body>
</html>
