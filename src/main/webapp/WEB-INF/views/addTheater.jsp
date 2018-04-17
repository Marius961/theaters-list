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

    <title>Title</title>
</head>
<body>
<div>
    <form:form action="/process-theater" method="post" modelAttribute="theater">
        <div>
            <form:hidden path="id" value="${theater.id}"/>
            <form:label path="name">Name of theater</form:label>
            <form:input path="name" type="text"/>
        </div>
        <div>
            <form:label path="tel">Tel num of theater:</form:label>
            <form:input path="tel" type="tel"/>
        </div>
        <div>
            <form:label path="address">Addres of theater</form:label>
            <form:input path="address" type="text"/>
        </div>
        <button type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
