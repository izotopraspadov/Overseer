<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="actualTimeList" scope="request" type="java.util.List"/>--%>
<html>
<head>
    <title>Актуальное время</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>
<br>
<br>
<br>
<br>
<div class="center">

    <c:if test="${empty actualTimeList}">
        <h1 class="h1" align="center">{Пусто}</h1>
    </c:if>
    <c:if test="${!empty actualTimeList}">
        <table class="tg">
            <tr>
                <th>ФИО</th>
                <th>Дата</th>
                <th>Актуальное время</th>
                <th>Учётное время</th>
            </tr>
            <c:forEach items="${actualTimeList}" var="time">
                <jsp:useBean id="time" class="edu.born.overseer.model.ActualTime"/>
                <tr>
                    <td>${time.employee.fullName}</td>
                    <td>${time.date}</td>
                    <td>${time.actualManHours}</td>
                    <td>${time.accountManHours}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
