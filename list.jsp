<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>倉庫リスト</title>
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
		function redirectToNewPage() {
			// 다른 페이지의 URL을 여기에 지정
			var newPageURL = "listtoroku.jsp";
			window.location.href = newPageURL;
		}
	</script>
	<script>
		function openNewWindow() {
			// 새로운 윈도우를 열고 해당 윈도우에 새로운 페이지를 로드합니다.
			var newPageURL = "listkensaku.jsp";
			window.open(newPageURL, "_blank", "width=600,height=400");
		}
	</script>
	<div align="center">
		<h1>倉庫リスト</h1>
		<table border="1" width="60%">
			<tr>
				<th class="centered-cell">倉庫コード</th>
				<th class="centered-cell">倉庫名</th>
				<th class="centered-cell">倉庫住所</th>
				<th class="centered-cell">使用有無</th>
			</tr>
			<tr>
				<td class="centered-cell">행 1, 열 1</td>
				<td class="centered-cell">兵庫倉庫</td>
				<td class="centered-cell">兵庫</td>
				<td class="centered-cell">有</td>
			</tr>
			<tr>
				<td class="centered-cell">행 2, 열 1</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">大阪</td>
				<td class="centered-cell">有</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">群馬倉庫</td>
				<td class="centered-cell">群馬</td>
				<td class="centered-cell">有</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">京都倉庫</td>
				<td class="centered-cell">京都</td>
				<td class="centered-cell">無</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">札幌倉庫</td>
				<td class="centered-cell">札幌</td>
				<td class="centered-cell">有</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">千葉倉庫</td>
				<td class="centered-cell">千葉</td>
				<td class="centered-cell">無</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">青森倉庫</td>
				<td class="centered-cell">青森</td>
				<td class="centered-cell">無</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">横浜倉庫</td>
				<td class="centered-cell">横浜</td>
				<td class="centered-cell">有</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">奈良倉庫</td>
				<td class="centered-cell">奈良</td>
				<td class="centered-cell">無</td>
			</tr>
			<tr>
				<td class="centered-cell">행 3, 열 1</td>
				<td class="centered-cell">福岡倉庫</td>
				<td class="centered-cell">福岡</td>
				<td class="centered-cell">無</td>
			</tr>
		</table>



		<table border="1" width="60%">
			<tr>
				<td class="righted-cell"><select name="検索降伏">
						<option value="">倉庫コード</option>
						<option value="">倉庫名</option>
						<option value="">倉庫住所</option>
						<option value="">使用有無</option>
				</select> <input type="search"> <input type="button" value="検索" onclick="openNewWindow()">
					<input type="button" value="登録" onclick="redirectToNewPage()"></td>
			</tr>
		</table>

	</div>


</body>
</html>