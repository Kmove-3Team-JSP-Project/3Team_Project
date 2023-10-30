<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>倉庫移動</title>
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
			var newPageURL = "idoutoroku.jsp";
			window.location.href = newPageURL;
		}
	</script>
	<script>
		function openNewWindow() {
			// 새로운 윈도우를 열고 해당 윈도우에 새로운 페이지를 로드합니다.
			var newPageURL = "idoukensaku.jsp";
			window.open(newPageURL, "_blank", "width=600,height=400");
		}
	</script>
	<div align="center">
		<h1>倉庫移動</h1>
		<table border="1" width="60%">
			<tr>
				<th class="centered-cell">移動日付</th>
				<th class="centered-cell">送る倉庫</th>
				<th class="centered-cell">受け取る倉庫</th>
				<th class="centered-cell">品目名</th>
				<th class="centered-cell">数量</th>
				<th class="centered-cell">担当者</th>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">福岡倉庫</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">携帯電話</td>
				<td class="centered-cell">100</td>
				<td class="centered-cell">原殿 藍造</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">兵庫倉庫</td>
				<td class="centered-cell">横浜倉庫</td>
				<td class="centered-cell">冷蔵庫</td>
				<td class="centered-cell">77</td>
				<td class="centered-cell">初田 夏那</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">横浜倉庫</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">ティーブイ</td>
				<td class="centered-cell">56</td>
				<td class="centered-cell">梅里 絃里</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">兵庫倉庫</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">パソコン</td>
				<td class="centered-cell">90</td>
				<td class="centered-cell">三河尻 厳覚</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">札幌倉庫</td>
				<td class="centered-cell">京都倉庫</td>
				<td class="centered-cell">エアコン</td>
				<td class="centered-cell">50</td>
				<td class="centered-cell">藪塚 朝哉</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">福岡倉庫</td>
				<td class="centered-cell">札幌倉庫</td>
				<td class="centered-cell">ギター</td>
				<td class="centered-cell">152</td>
				<td class="centered-cell">雨粕 花綺</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">京都倉庫</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">鉛筆</td>
				<td class="centered-cell">500</td>
				<td class="centered-cell">貝沼 玄</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">兵庫倉庫</td>
				<td class="centered-cell">千葉倉庫</td>
				<td class="centered-cell">靴</td>
				<td class="centered-cell">320</td>
				<td class="centered-cell">飯淵 柚姫菜</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">奈良倉庫</td>
				<td class="centered-cell">大阪倉庫</td>
				<td class="centered-cell">扇風機</td>
				<td class="centered-cell">55</td>
				<td class="centered-cell">原殿 隆吾</td>
			</tr>
			<tr>
				<td class="centered-cell">yyyy-MM-dd</td>
				<td class="centered-cell">千葉倉庫</td>
				<td class="centered-cell">奈良倉庫</td>
				<td class="centered-cell">パジ</td>
				<td class="centered-cell">150</td>
				<td class="centered-cell">坂嶋 為墨</td>
			</tr>
			
		</table>



		<table border="1" width="60%">
			<tr>
				<td class="righted-cell"><select name="検索降伏">
						<option value="">移動日付</option>
						<option value="">送る倉庫</option>
						<option value="">受け取る倉庫</option>
						<option value="">品目名</option>
						<option value="">数量</option>
						<option value="">担当者</option>
				</select> <input type="search"> <input type="button" value="検索" onclick="openNewWindow()">
					<input type="button" value="登録" onclick="redirectToNewPage()"></td>
			</tr>
		</table>

	</div>


</body>
</html>