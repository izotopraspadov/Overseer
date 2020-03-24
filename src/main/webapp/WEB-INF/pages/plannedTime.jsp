<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="plannedTimeList" scope="request" type="java.util.List"/>--%>
<html>
<head>
    <title>Планируемое время</title>
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
<c:if test="${!empty plannedTimeList}">
    <table class="tg">
        <tr>
            <th>ФИО</th>
            <th>Планируемое время</th>
        </tr>
        <c:forEach items="${plannedTimeList}" var="time">
            <jsp:useBean id="time" class="edu.born.overseer.model.PlannedTime"/>
            <tr>
                <td>${time.employee.fullName}</td>
                <td>${time.manHours}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
