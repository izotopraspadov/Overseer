<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="tasks" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Задачи</title>
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
<c:if test="${!empty tasks}">
    <table class="tg">
        <tr>
            <th>Ответственное лицо</th>
            <th>Описание</th>
            <th>Дата завершения</th>
            <th>Результат</th>
            <th>Кому РГ</th>
            <th>Кому Менеджер</th>
            <th>Комментарий</th>
        </tr>
        <c:forEach items="${tasks}" var="task">
            <jsp:useBean id="task" class="edu.guap.enclave.model.Task"/>
            <tr>
                <td>${task.employee.fullName}</td>
                <td>${task.taskDescription}</td>
                <td>${task.dateCompleted}</td>
                <td>${task.result.name()}</td>
                <td>
                    <c:forEach items="${task.taskEmails}" var="email">
                        <c:if test="${email.typeSend eq 'TEAM_LEADER'}">
                            ${email.email.email};
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${task.taskEmails}" var="email">
                        <c:if test="${email.typeSend eq 'MANAGER'}">
                            ${email.email.email};
                        </c:if>
                    </c:forEach>
                </td>
                <td>${task.comment}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
