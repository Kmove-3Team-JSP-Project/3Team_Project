<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="header.css">
<!-- CSS 파일 연결 -->
</head>
<body>
<u:isLogin> <!-- 커스텀 태그로 로그인 여부 확인 -->
<div class="header">
		<ul class="header-menu">
			<li><a href="change.do">会員情報の修正</a></li>
			<li><a href="companyList.do">取引先</a></li>
			<li><a href="orderList.do">発注要請</a></li>
			<li><a href="planList.do">発注計画</a></li>
			<li><a href="sheetList.do">発注書</a></li>
			<li><a href="buyList.do">購買リスト</a></li>
			<li><a href="itemList.do">品目リスト</a></li>
			<li><a href="stockList.do">在庫リスト</a></li>
			<li><a href="storageList.do">倉庫リスト</a></li>
			<li><a href="boxMoveList.do">倉庫移動</a></li>
			<li><a href="recallList.do">不良処理サービス</a></li>
			<li><a href="logout.do">ログアウト</a></li>
			<li><a href="./index.jsp">メーン</a></li>
		</ul>
	</div>
</u:isLogin>

<u:notLogin>
<div class="header">
		<ul class="header-menu">
			<li><a href="change.do">会員情報の修正</a></li>
			<li><a href="companyList.do">取引先</a></li>
			<li><a href="orderList.do">発注要請</a></li>
			<li><a href="planList.do">発注計画</a></li>
			<li><a href="sheetList.do">発注書</a></li>
			<li><a href="buyList.do">購買リスト</a></li>
			<li><a href="itemList.do">品目リスト</a></li>
			<li><a href="stockList.do">在庫リスト</a></li>
			<li><a href="storageList.do">倉庫リスト</a></li>
			<li><a href="boxMoveList.do">倉庫移動</a></li>
			<li><a href="recallList.do">不良処理サービス</a></li>
			<li><a href="login.do">ログイン</a></li>
			<li><a href="./index.jsp">メーン</a></li>
		</ul>
	</div>
</u:notLogin>

</body>
</html>
