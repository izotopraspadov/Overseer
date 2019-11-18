<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="employee" class="edu.guap.enclave.model.Employee"/>--%>
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
<form action="/admin/employees/update" method="POST">
    <table class="center">

        <tr>

            <td>ID:</td>
            <td><input type="text" name="id" value="${employee.id}" readonly/>
            </td>


        </tr>

        <tr>
            <td>ФИО:</td>
            <td><input type="text" name="fullName" value="${employee.fullName}"/></td>
        </tr>
        <tr>
            <td>Логин:</td>
            <td>
                <input type="text" name="login" value="${employee.login}"/>

            </td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td>
                <input type="text" name="password" value="${employee.password}"/>

            </td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type="text" name="address" value="${employee.address}"/></td>
        </tr>
        <tr>
            <td>Регион:</td>
            <td><input type="text" name="region" value="${employee.region.title}"/></td>
        </tr>
        <tr>
            <td>Телефоны:</td>
            <c:set var="count" value="${1}" scope="page"/>
            <c:forEach items="${employee.phones}" var="phone">
                <td><input type="text" name="phone${count}" value="${phone.number}"/></td>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
        </tr>
        <tr>
            <td>Почтовые адреса:</td>
            <c:set var="count" value="${1}" scope="page"/>
            <c:forEach items="${employee.emails}" var="email">
                <td><input type="text" name="email${count}" value="${email.email}"/></td>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
        </tr>
        <tr>
            <td>Зарплата:</td>
            <c:forEach items="${employee.salary}" var="salary">
                <td><input type="text" name="salary" value="${salary.amount}"/></td>
            </c:forEach>

        </tr>
        <tr>
            <td>
                <input type="submit" value="Изменить"/>
            </td>
        </tr>
    </table>

</form>

</body>
</html>
