<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 14.04.2018
  Time: 15:55
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
    <div class="main-content-about fixed" id="head-block">
        <label class="content-label-about info-label-header">Вистави у театрі:</label>
        <label class="content-label-about">${theater.name}<img class="btn-menu" onclick="closeOpen()" src="<%=request.getContextPath()%>/resources/images/menu.png"></label>
    </div>
    <div class="btn-bar" id="buttons">
        <div class="table-cell">
            <button class="content-btn btn-back" onclick="location.href='/home'">На головну</button>
        </div>
        <form:form action="/delete-theater" modelAttribute="deletedTheater" method="post" cssClass="table-cell">
            <form:hidden path="deletedTheaterId" value="${theater.id}"/>
            <button type="submit" class="content-btn btn-delete">Видалити театр</button>
        </form:form>
        <form:form method="get" modelAttribute="theater" action="/add-theater" cssClass="table-cell">
            <form:hidden path="id" value="${theater.id}"/>
            <form:hidden path="name" value="${theater.name}"/>
            <form:hidden path="tel" value="${theater.tel}"/>
            <form:hidden path="address" value="${theater.address}"/>
            <button class="content-btn btn-edit">Редагувати театр</button>
        </form:form>
    </div>
    <div class="container-1 fixed">
        <label class="label-1">Назва вистави</label>
        <label class="label-1">Постановщик</label>
        <label class="label-1">Дата</label>
    </div>
</header>
<main>
    <c:forEach var="play" items="${plays}">
        <div class="main-content" onclick="location.href='/get-play/${play.id}'">
            <label class="content-label">${play.name}</label>
            <label class="content-label">${play.productionDirector}</label>
            <label class="content-label">${play.date}</label>
        </div>
    </c:forEach>
</main>

<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>
