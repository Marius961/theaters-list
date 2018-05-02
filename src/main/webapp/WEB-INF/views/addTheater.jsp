<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 02.04.2018
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8"%>
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
        <label class="label-1">Дадавання театру</label>
        <button class="label-1" style="background-color: #ffa433; color: black; border: 0" onclick="location.href='/'">Назад</button>
    </div>
    <div class="content-label-about info-label-header">
        <form:form action="/process-theater" method="post" modelAttribute="theater" cssClass="main-content-about" cssStyle="padding: 2%">
            <div class="form-group">
                <form:hidden path="id" value="${theater.id}"/>
                <form:label path="name">Назва театру:</form:label>
                <form:input path="name" type="text" cssClass="form-control"/>
                <form:errors path="name" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="tel">Номер телефону театру:</form:label>
                <form:input path="tel" type="text" cssClass="form-control"/>
                <form:errors path="tel" cssClass="error-1"/>
            </div>
            <div class="form-group">
                <form:label path="address">Адреса театру:</form:label>
                <form:input path="address" type="text" cssClass="form-control"/>
                <form:errors path="address" cssClass="error-1"/>
            </div>
            <button class="btn btn-dark" style="margin: 1%" type="submit">Додати</button>
        </form:form>
    </div>
</main>
</body>
</html>
