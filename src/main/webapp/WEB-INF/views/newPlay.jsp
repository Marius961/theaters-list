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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
</head>
<body>
    <div>
        <form:form action="/process-play" method="post" modelAttribute="play">
            <div>
                <form:hidden path="id" value="${play.id}"/>
                <form:label path="theaterId">Виберіть театр</form:label>
                <form:select path="theaterId">
                    <c:forEach var="theater" items="${theaters}">
                        <form:option value="${theater.id}" label="${theater.name}"/>
                    </c:forEach>
                </form:select>
            </div>
            <div>
                <form:label path="name">Ім'я вистави</form:label>
                <form:input path="name" type="text"/>
                <form:errors path="name" cssClass="error-1"/>
            </div>
            <div>
                <form:label path="date">Дата вистави</form:label>
                <form:input path="date" type="date"/>
                <form:errors path="date" cssClass="error-1"/>
            </div>
            <div>
                <form:label path="productionDirector">Постановщик(и):</form:label>
                <form:input path="productionDirector" type="text"/>
                <form:errors path="productionDirector" cssClass="error-1"/>
            </div>
            <div>
                <form:label path="description">Опис:</form:label>
                <form:input path="description" type="text"/>
                <form:errors path="description" cssClass="error-1"/>
            </div>
            <button type="submit">Submit</button>
         </form:form>
    </div>
</body>
</html>
