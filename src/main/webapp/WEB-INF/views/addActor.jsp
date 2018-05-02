<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 02.04.2018
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <title>Title</title>
</head>
<body>
<main style="width: 70%; margin: 2% 15%">
    <div class="container-1 fixed">
        <c:if test="${empty actorAndPlay.playName}">
            <label class="label-1">Редагування актора</label>
        </c:if>
        <c:if test="${not empty actorAndPlay.playName}">
            <label class="label-1">Актор у виставі ${actorAndPlay.playName}</label>
        </c:if>
        <button class="label-1" style="background-color: #ffa433; color: black; border: 0" onclick="location.href='/'">Назад</button>
    </div>
    <div class="content-label-about info-label-header">
        <form:form action="/process-actor" method="post" modelAttribute="actorAndPlay" cssClass="main-content-about">
            <div>
                <form:hidden path="redorectPlayId" value="${actorAndPlay.redorectPlayId}"/>
                <form:hidden path="actorId" value="${actorAndPlay.actorId}"/>
                <form:label path="actorName">Ім'я актора</form:label>
                <form:input path="actorName" type="text"/>
                <br>
                <form:errors path="actorName" cssClass="error-1"/>
            </div>
            <form:hidden path="playName" value="${actorAndPlay.playName}"/>
            <form:hidden path="playId" value="${actorAndPlay.playId}"/>
            <button class="btn btn-dark" style="margin: 1%" type="submit">Додати</button>
        </form:form>
    </div>
</main>
</body>
</html>
