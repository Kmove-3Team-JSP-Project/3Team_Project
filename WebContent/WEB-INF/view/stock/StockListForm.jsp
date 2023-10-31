<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StockList</title>
</head>
<body>
	<h1>
		<div align='center'>[在庫リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>

			<tr>
				<th>在庫コード</th>
				<th>在庫名</th>
				<th>倉庫名</th>
				<th>在庫数量</th>
				<th>単価</th>
			</tr>
			<tr>
				<th></th>
				<th>ピアノ</th>
				<th>D</th>
				<th>450</th>
				<th>50000</th>
			</tr>
			<tr>
				<th></th>
				<th>バナナ</th>
				<th>A, C</th>
				<th>376</th>
				<th>1000</th>
			</tr>
			<tr>
				<th></th>
				<th>リンゴ</th>
				<th>A, C</th>
				<th>175</th>
				<th>500</th>
			</tr>
			<tr>
				<th></th>
				<th>ニンテンド</th>
				<th>B</th>
				<th>35</th>
				<th>35000</th>
			</tr>
			<tr>
				<th></th>
				<th>筆筒</th>
				<th>B</th>
				<th>500</th>
				<th>350</th>
			</tr>
			<tr>
				<th></th>
				<th>パソコン</th>
				<th>B, D</th>
				<th>100</th>
				<th>100000</th>
			</tr>
			<tr>
				<th></th>
				<th>果物ナイフ</th>
				<th>A, B, C</th>
				<th>32</th>
				<th>1500</th>
			</tr>
		</table>
	</div>
	&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
	<div align='right'>
		<form action="StockSearch.jsp" method="post">
			<label for="検索条件">検索条件:</label> <select name="検索条件" id="検索条件">
				<option value="選択">選択</option>
				<option value="在庫名">品目名</option>
				<option value="倉庫名">倉庫名</option>	
			</select> <input type="text" name="keyword" placeholder="入力"> <input
				type="submit" value="検索">
		</form>
	</div>

</body>
</html>