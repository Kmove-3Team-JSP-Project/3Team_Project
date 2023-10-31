<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service</title>
</head>
<body>
	<h1>
		<div align='center'>[在庫リスト]</div>
	</h1>
	<div align='center'>
		<table border='1' width='50%'>

			<tr>
				<th>品目名</th>
				<th>在庫コード</th>
				<th>倉庫名</th>
				<th><div>在庫数量</div>
					<div>修整量</div></th>
			</tr>
			<tr>
				<th>ピアノ</th>
				<th></th>
				<th>D</th>
				<th><div>
						450
						<div>
							<div>100</div></th>
			</tr>
			<div align='right'>
				<tr>
					<td colspan="4"><a href="RecallService.jsp">[数量修正]</a></td>
				</tr>
			</div>
		</table>
	</div>
</html>