<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="employee" class="Employee"/>--%>
<html>
<head>
    <title>Личная информация</title>
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
<table class="tg">
    <tr>
        <td>ФИО:</td>
        <td>${employee.fullName}</td>
    </tr>
    <tr>
        <td>Логин:</td>
        <td>${employee.login}</td>
    </tr>
    <tr>
        <td>Адрес:</td>
        <td>${employee.address}</td>
    </tr>
    <tr>
        <td>Регион:</td>
        <td>${employee.region.title}</td>
    </tr>
    <tr>
        <td>Телефоны:</td>
        <td>
            <c:forEach items="${employee.phones}" var="phone">
                ${phone.number};
            </c:forEach></td>
    </tr>
    <tr>
        <td>Почтовые адреса:</td>
        <td>
            <c:forEach items="${employee.emails}" var="email">
                ${email.email};
            </c:forEach></td>
    </tr>

</table>
</body>
</html>
