<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 29.03.2018
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
</head>
<body>
<header>
    <div class="container-1 fixed">
        <label class="label-1">Назва театру <button class="btn-add" onclick="location.href='/add-theater'">+</button></label>
        <label class="label-1">Адреса</label>
        <label class="label-1">Номер телефону</label>
        <label class="label-1">Кількість вистав <button class="btn-add" onclick="location.href='/add-play'">+</button></label>
    </div>
</header>
<main>
    <c:forEach var="theater" items="${theaters}">
        <div class="main-content" onclick="location.href='/get/${theater.id}'">
            <label class="content-label">${theater.name}</label>
            <label class="content-label">${theater.address}</label>
            <label class="content-label">${theater.tel}</label>
            <label class="content-label">${theater.playsCount}</label>
        </div>
    </c:forEach>
    <script rel="script" src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</main>
</body>
</html>
