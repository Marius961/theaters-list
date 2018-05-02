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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
    <form:form modelAttribute="searchedObject" method="post" action="/process-search" cssClass="main-content-about fixed">
        <div style="width: 40%; margin: 0 30%; display: inline-block">
            <form:input path="objectName" type="text" cssClass="content-label-about info-label-header" cssStyle="width: 65%"/>
            <button type="submit" class="btn btn-light" style="width: 20%; padding: 1%; font-size: 3vh">Знайти</button>
        </div>
    </form:form>
    <c:forEach var="theater" items="${theaters}">
        <div class="main-content" onclick="location.href='/get/${theater.id}'">
            <label class="content-label">${theater.name}</label>
            <label class="content-label">${theater.address}</label>
            <label class="content-label">${theater.tel}</label>
            <label class="content-label">${theater.playsCount}</label>
        </div>
    </c:forEach>
    <c:if test="${not empty searchedPlays}">
        <div class="main-content-about fixed">
            <label class="content-label-about info-label-header">Вистави:</label>
        </div>
        <c:forEach var="play" items="${searchedPlays}">
            <div class="main-content" onclick="location.href='/get-play/${play.id}'">
                <label class="content-label">${play.name}</label>
                <label class="content-label">${play.productionDirector}</label>
                <label class="content-label">${play.date}</label>
            </div>
        </c:forEach>
    </c:if>
    <script rel="script" src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</main>
</body>
</html>
