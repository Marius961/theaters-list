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
</head>
<body>
    <div>
        <form:form action="/process-play" method="post" modelAttribute="play">
            <div>
                <form:hidden path="id" value="${play.id}"/>
                <form:label path="theaterId">Select theater</form:label>
                <form:select path="theaterId">
                    <c:forEach var="theater" items="${theaters}">
                        <form:option value="${theater.id}" label="${theater.name}"/>
                    </c:forEach>
                </form:select>
            </div>
            <div>
                <form:label path="name">Name of play</form:label>
                <form:input path="name" type="text"/>
            </div>
            <div>
                <form:label path="date">Date of play</form:label>
                <form:input path="date" type="date"/>
            </div>
            <div>
                <form:label path="productionDirector">Name of production director</form:label>
                <form:input path="productionDirector" type="text"/>
            </div>
            <div>
                <form:label path="description">Description</form:label>
                <form:input path="description" type="text"/>
            </div>
            <button type="submit">Submit</button>
         </form:form>
    </div>
</body>
</html>
