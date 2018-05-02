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
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
</head>
<body>
<main style="width: 70%; margin: 2% 15%">
    <div class="container-1 fixed">
        <label class="label-1">Дадавання вистави</label>
        <button class="label-1" style="background-color: #ffa433; color: black; border: 0" onclick="location.href='/'">Назад</button>
    </div>
    <div class="content-label-about info-label-header">
        <form:form action="/process-play" method="post" modelAttribute="play" cssClass="main-content-about" cssStyle="padding: 2%">
            <div class="input-group mb-3">
                <form:hidden path="id" value="${play.id}"/>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Театри</label>
                </div>
                <form:select path="theaterId" cssClass="custom-select" id="inputGroupSelect01">
                    <c:forEach var="theater" items="${theaters}">
                        <form:option value="${theater.id}" label="${theater.name}"/>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <form:label path="name">Ім'я вистави</form:label>
                <form:input path="name" type="text" cssClass="form-control"/>
                <form:errors path="name" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="date">Дата вистави</form:label>
                <form:input path="date" type="date" cssClass="form-control"/>
                <form:errors path="date" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="productionDirector">Постановщик(и):</form:label>
                <form:input path="productionDirector" type="text" cssClass="form-control"/>
                <form:errors path="productionDirector" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="description">Опис:</form:label>
                <form:input path="description" type="text" cssClass="form-control"/>
                <form:errors path="description" cssClass="error-1"/>
            </div>
            <button class="btn btn-dark" style="margin: 1%" type="submit">Додати</button>
        </form:form>
    </div>
</main>
</body>
</html>
