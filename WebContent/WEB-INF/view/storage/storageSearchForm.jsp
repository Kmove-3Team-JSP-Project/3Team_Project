<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>倉庫検索結果</title>
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
<div align="center">
    <h2>倉庫検索結果</h2>
	
    <table border="1" width="60%">
        <tr>
            <th class="centered-cell">倉庫コード</th>
            <th class="centered-cell">倉庫名</th>
            <th class="centered-cell">倉庫住所</th>
            <th class="centered-cell">使用有無</th>
        </tr>
        <c:forEach var="storage" items="${searchResult}">
            <tr>
                <td class="centered-cell">${storage.storageCode}</td>
                <td class="centered-cell">${storage.storageName}</td>
                <td class="centered-cell">${storage.storageAddress}</td>
                <td class="centered-cell">${storage.storageUse}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
