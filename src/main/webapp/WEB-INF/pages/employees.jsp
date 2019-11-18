<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="employees" scope="request" type="java.util.List"/>--%>
<html>
<head>
    <title>Сотрудники</title>
    <style>
        <%@include file="/WEB-INF/pages/css/style.css"%>
    </style>
    <script language="javascript" type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.js"></script>
</head>
<body>
<script>
    $(function () {
        $('#category').change(function () {
            var selection = $(this).val();
            switch (selection) {
                case "region":
                    $("#data").hide();
                    break;
                default:
                    $("#data").show();
            }
        });
    });
</script>
<jsp:include page="menu.jsp"/>

<table class="find">
    <tr>
        <form action="/employees/token" method="POST">
            <th><p>Поиск: </p></th>
            <th>
                <div id="data">
                    <input type="text" name="data"/>
                </div>
            </th>
            <th>
                <select name="category" id="category">
                    <option value="full_name" type="text" name="select">По ФИО</option>
                    <option value="login" type="text" name="select">По логину</option>
                    <option value="address" type="text" name="select">По адресу</option>
                    <option value="region" type="text" name="select">По региону</option>
                </select>
            </th>
            <th>
                <input type="submit" value="Найти"/>
            </th>
        </form>
    </tr>
</table>
<br>
<br>
<br>
<br>
<div class="center">
    <c:if test="${!empty employees}">
        <table class="tg">
            <tr>
                <th width="80">ФИО</th>
                <th width="120">Регион</th>
                <th width="120">Логин</th>
                <th width="120">Адрес</th>
                <th width="120">Платежи</th>
            </tr>
            <c:forEach items="${employees}" var="employee">
                <jsp:useBean id="employee" class="edu.guap.enclave.model.Employee"/>
                <tr>
                    <td>${employee.fullName}</td>
                    <td>${employee.region.title}</td>
                    <td>${employee.login}</td>
                    <td>${employee.address}</td>
                    <td><a href="/finance/employee/${employee.id}">[Перейти]</a></td>
                    <c:if test="${swtch ne 'obj'}">
                        <td><a href="/admin/employees/${employee.id}">[Изменить]</a></td>
                    </c:if>
                    <c:if test="${swtch eq 'obj'}">
                        <td><a href="/objects/manager/${employee.id}">[Выбрать]</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
