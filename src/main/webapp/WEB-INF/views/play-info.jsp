<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mapiy
  Date: 14.04.2018
  Time: 17:44
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
    <header>
        <div class="container-1 fixed">
            <label class="label-1">Про виставу: ${play.name}</label>
        </div>
    </header>
    <main>
        <div class="main-content-about">
            <label class="content-label-about info-label-header">Опис: <img class="btn-menu" style="width: 2%; padding: 0.5%" onclick="closeOpen()" src="<%=request.getContextPath()%>/resources/images/menu.png"></label>
        </div>
        <div class="main-content-about">
            <label class="content-label-about">${play.description}</label>
        </div>
        <div class="main-content-about">
            <label class="content-label-about info-label-header">
                Актори:
                <form:form action="/add-actor" modelAttribute="actorAndPlay" method="get" cssClass="btn-inline">
                    <form:hidden path="playId" value="${play.id}"/>
                    <form:hidden path="playName" value="${play.name}"/>
                    <button class="btn-add" type="submit">+</button>
                </form:form>
            </label>
            <div class="content-label-about">
                <c:forEach var="actor" items="${actors}">
                    <div class="main-content-about">
                        <label class="content-label-about actor-name">${actor.name}</label>
                        <div class="content-label-about actor-action">
                            <form:form method="get" action="/add-actor" modelAttribute="actorAndPlay">
                                <form:hidden path="redorectPlayId" value="${play.id}"/>
                                <form:hidden path="actorId" value="${actor.id}"/>
                                <form:hidden path="actorName" value="${actor.name}"/>
                                <button type="submit" class="btn-3 btn-act-edit">Редагувати</button>
                            </form:form>
                        </div>
                        <div class="content-label-about actor-action">
                            <form:form method="post" action="/delete-actor" modelAttribute="deletedActor">
                                <form:hidden path="dActorId" value="${actor.id}"/>
                                <form:hidden path="dPlayId" value="${play.id}"/>
                                <button type="submit" class="btn-3 btn-act-delete">Видалити</button>
                            </form:form>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="btn-bar" id="buttons">
            <button class="content-btn btn-back" onclick="location.href='/home'">Назад</button>
            <form:form action="/delete-play" modelAttribute="deletedPlay" method="post">
                <form:hidden path="deletedPlayId" value="${play.id}"/>
                <button type="submit" class="content-btn btn-delete">Видалити виставу</button>
            </form:form>
            <form:form action="/add-play" modelAttribute="play" method="get">
                <form:hidden path="id" value="${play.id}"/>
                <form:hidden path="name" value="${play.name}"/>
                <form:hidden path="description" value="${play.description}"/>
                <form:hidden path="date" value="${play.date}"/>
                <form:hidden path="productionDirector" value="${play.productionDirector}"/>
                <form:hidden path="theaterId" value="${play.theaterId}"/>
                <button type="submit" class="content-btn btn-edit">Редагувати виставу</button>
            </form:form>
        </div>
    </main>

    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</header>
</body>
</html>
