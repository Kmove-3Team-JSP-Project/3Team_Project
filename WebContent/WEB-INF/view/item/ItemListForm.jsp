<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>品目リスト</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<h1>
		<div align='center'>[品目リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>

			<tr>
				<th>品目コード</th>
				<th>品目名</th>
				<th>単価</th>
				<th>品目区分</th>
			</tr>
			<tr>
				<th></th>
				<th>ピアノ</th>
				<th>50000</th>
				<th>楽器</th>
			</tr>
			<tr>
				<th></th>
				<th>バナナ</th>
				<th>1000</th>
				<th>果物</th>
			</tr>
			<tr>
				<th></th>
				<th>リンゴ</th>
				<th>500</th>
				<th>果物</th>
			</tr>
			<tr>
				<th></th>
				<th>ニンテンド</th>
				<th>35000</th>
				<th>ゲーム機</th>
			</tr>
			<tr>
				<th></th>
				<th>筆筒</th>
				<th>350</th>
				<th>学用品</th>
			</tr>
			<tr>
				<th></th>
				<th>パソコン</th>
				<th>100000</th>
				<th>電子機器</th>
			</tr>
		</table>
	</div>
	&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
	<div align='right'>
		<form action="ItemSearch.jsp" method="post">
			<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
				<option value="選択">選択</option>
				<option value="品目名">品目名</option>
				<option value="品目区分">品目区分</option>
			</select> <input type="text" name="keyword" placeholder="入力"> <input
				type="submit" value="検索">
		</form>
	</div>
</body>
</html>