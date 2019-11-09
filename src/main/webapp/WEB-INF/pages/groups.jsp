<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groups" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Группы</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
</head>
<body>
<c:if test="${!empty groups}">
    <table class="tg">
        <tr>
            <th width="80">Название</th>
            <th width="120">Тип объектов</th>
            <th width="120">Комментарий</th>
        </tr>
        <c:forEach items="${groups}" var="group">
            <jsp:useBean id="group" class="edu.guap.enclave.model.Group"/>
            <tr>
                <td>${group.title}</td>
                <td>
                <c:forEach items="${group.types}" var="type">
                    ${type.title};
                </c:forEach>
                </td>
                <td>${group.comment}</td>
                <td><a href="/enclave/objects/group/${group.id}">[Выбрать]</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
