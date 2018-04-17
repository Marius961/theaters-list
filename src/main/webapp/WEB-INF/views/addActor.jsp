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
    <form:form action="/process-actor" method="post" modelAttribute="actorAndPlay">
        <c:if test="${empty actorAndPlay.playName}">
            <h1>Editing actor</h1>
        </c:if>
        <c:if test="${not empty actorAndPlay.playName}">
            <h1>Actor for ${actorAndPlay.playName}</h1>
        </c:if>
        <div>
            <form:hidden path="redorectPlayId" value="${actorAndPlay.redorectPlayId}"/>
            <form:hidden path="actorId" value="${actorAndPlay.actorId}"/>
            <form:label path="actorName">Actor name</form:label>
            <form:input path="actorName" type="text"/>
        </div>
        <form:hidden path="playName" value="${actorAndPlay.playName}"/>
        <form:hidden path="playId" value="${actorAndPlay.playId}"/>

        <button type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
