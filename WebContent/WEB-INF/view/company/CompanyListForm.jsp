<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先リスト</title>
</head>
<body>
	<h1>
		<div align="center">[取引先リスト]</div>
	</h1>

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



		<div align="center">
			<form action="CompanySearchForm.jsp" method="post">
				<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
					<option value="取引先コード">取引先コード</option>
					<option value="取引先名">取引先名</option>
					<option value="担当者">担当者</option>
					<option value="電話番号">電話番号</option>
					<option value="所有倉庫">所有倉庫</option>
				</select> <input type="text" name="keyword" placeholder="入力"> <input
					type="submit" value="検索"> 
					<input type="button" value="登録" onclick="window.location.href='CompanyRegisterForm.jsp'" />


					


			</form>

		</div>


	</div>

</body>
</html>
